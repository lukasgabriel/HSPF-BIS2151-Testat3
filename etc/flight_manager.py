# flight_manager.py

'''
This console application manages the information used by an air travel catering company.
'''

import pandas as pd

import urllib.request

from datetime import datetime, timedelta
from uuid import UUID


airports_url = 'https://raw.githubusercontent.com/datasets/airport-codes/master/data/airport-codes.csv'
airports_fallback = 'etc\data\\airport-codes.csv'


def load_airports():
    filename = airports_url  # Try to get current list from dataset mirror on github
    if urllib.request.urlopen(airports_url).getcode() != 200:
        print('Cannot reach airport dataset. Using local copy...')
        filename = airports_fallback  # Local copy as fallback if that fails for any reason

    # Read csv source file into a pandas dataframe for faster lookup
    airport_reader = pd.read_csv(filename, usecols=lambda column: column not in [
                                 'elevation_ft', 'gps_code', 'local_code', 'coordinates', 'ident'], engine='c')
    # Drop airports without IATA code (small airports/helipads etc)
    airports_iata = airport_reader.dropna(subset=['iata_code'])
    airports_indexed = airports_iata.set_index('iata_code')
    return airports_indexed


def get_airport_data(flight_data):
    orig = flight_data['origin_airport']
    dest = flight_data['destination_airport']
    # print(dict(airports.loc[dest]))


class Flight(object):

    flight_data_fields = {'flight_id': UUID, 'flight_name': str, 'flight_shortname': str, 'passengers': dict, 'passenger_count': int, 'origin_airport': str,
                          'destination_airport': str, 'flight_duration': timedelta, 'departure_time': datetime, 'arrival_time': datetime, 'is_intercont': bool, 'airport_data': dict}

    __slots__ = flight_data_fields.keys()

    def __init__(self, flight_data: dict):
        self.flight_id = UUID()
        self.passengers = flight_data['passengers']
        self.origin_airport = flight_data['origin_airport']
        self.destination_airport = flight_data['destination_airport']
        self.departure_time = flight_data['departure_time']
        self.arrival_time = flight_data['arrival_time']

        # self.airport_data = get_airport_data(self.flight_data)

        self.passenger_count = sum(self.passengers.values())
        # self.flight_name =
        # self.flight_shortname =
        # self.flight_duration =


airports = load_airports()
print(airports)

# get_airport_data({'origin_airport': 'LAX', 'destination_airport': 'BER'})
