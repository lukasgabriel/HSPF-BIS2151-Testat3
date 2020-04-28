# flight_manager.py

'''
This console application manages the information used by an air travel catering company.
'''

import pandas as pd

import urllib.request

from datetime import datetime, timedelta

from hashids import Hashids


# airports_url = 'https://raw.githubusercontent.com/datasets/airport-codes/master/data/airport-codes.csv'  # Only using local copy while testing
airports_fallback = 'etc\data\\airport-codes.csv'

# countries_url = 'https://raw.githubusercontent.com/datasets/country-codes/master/data/country-codes.csv'  # Only using local copy while testing
countries_fallback = 'etc\data\\country-codes.csv'


def load_airports():
    try:
        filename = airports_url  # Try to get current list from dataset mirror on github
        urllib.request.urlopen(airports_url).getcode()
    except:
        print('Cannot reach airport dataset. Using local copy...')
        filename = airports_fallback  # Local copy as fallback if that fails for any reason

    # Read csv source file into a pandas dataframe for faster lookup
    airport_reader = pd.read_csv(filename, usecols=lambda column: column not in [
                                 'elevation_ft', 'gps_code', 'local_code', 'coordinates', 'ident'], engine='c', keep_default_na=False)
    # Drop airports without IATA code (small airports/helipads etc)
    airports_iata = airport_reader.dropna(subset=['iata_code'])
    airports_indexed = airports_iata.set_index('iata_code')
    return airports_indexed

airports = load_airports()


def load_countries():
    try:
        filename = countries_url  # Try to get current list from dataset mirror on github
        urllib.request.urlopen(countries_url).getcode()
    except:
        print('Cannot reach country dataset. Using local copy...')
        filename = countries_fallback  # Local copy as fallback if that fails for any reason

    # Read csv source file into a pandas dataframe for faster lookup
    country_reader = pd.read_csv(filename, usecols=['ISO3166-1-Alpha-2','CLDR display name'], engine='c', keep_default_na=False)
    countries_indexed = country_reader.set_index('ISO3166-1-Alpha-2')
    return countries_indexed

countries = load_countries()


def get_airport_data(origin_airport_iata, destination_airport_iata):
    origin_data = dict(airports.loc[origin_airport_iata])
    destination_data = dict(airports.loc[destination_airport_iata])
    return origin_data, destination_data


def get_country_data(origin_iso_country, destination_iso_country):
    origin_country = dict(countries.loc[origin_iso_country])['CLDR display name']
    destination_country = dict(countries.loc[destination_iso_country])['CLDR display name']
    return origin_country, destination_country


class Flight(object):

    flight_hashid = Hashids()
    flight_data_fields = [
        'flight_id', 
        'flight_shortname', 
        'flight_description', 
        'origin_airport_iata', 
        'destination_airport_iata', 
        'origin_data', 
        'destination_data', 
        'origin_airport_name', 
        'destination_airport_name', 
        'origin_iso_country', 
        'destination_iso_country', 
        'origin_country', 
        'destination_country',
        'origin_municipality', 
        'destination_municipality', 
        'origin_iso_region', 
        'destination_iso_region',
        'departure_time', 
        'arrival_time', 
        'flight_duration', 
        'is_international', 
        'is_intercontinental', 
        'flight_type', 
        'passengers', 
        'passenger_count' 
        ]

    __slots__ = flight_data_fields

    def __init__(self, flight_data: dict):

        self.origin_airport_iata = flight_data['origin_airport_iata']
        self.destination_airport_iata = flight_data['destination_airport_iata']
        self.origin_data, self.destination_data = get_airport_data(self.origin_airport_iata, self.destination_airport_iata)
        
        self.origin_airport_name = self.origin_data['name']
        self.destination_airport_name = self.destination_data['name']

        self.origin_iso_country = self.origin_data['iso_country']
        self.destination_iso_country = self.destination_data['iso_country']
        self.origin_country, self.destination_country = get_country_data(self.origin_iso_country, self.destination_iso_country)

        self.origin_iso_region = self.origin_data['iso_region']
        self.destination_iso_region = self.destination_data['iso_region']
        self.origin_municipality = self.origin_data['municipality']
        self.destination_municipality = self.destination_data['municipality']

        self.passengers = flight_data['passengers']
        self.passenger_count = sum(self.passengers.values())

        self.departure_time = flight_data['departure_time']
        self.arrival_time = flight_data['arrival_time']
        self.flight_duration = self.arrival_time - self.departure_time

        self.is_intercontinental = True if self.origin_data['continent'] != self.destination_data['continent'] else False
        self.is_international = True if self.origin_data['iso_country'] != self.destination_data['iso_country'] else False
        self.flight_type = 'Intercontinental' if self.is_intercontinental else 'International' if self.is_international else 'Domestic'
        
        iata_to_ints = ''
        for x in [ord(char) + 2 for char in (self.origin_airport_iata + self.destination_airport_iata).lower()]:
            iata_to_ints += (str(x))

        self.flight_id = self.flight_hashid.encode(self.passenger_count, int(self.departure_time.strftime("%y%m%d%H%M")), int(self.flight_duration.total_seconds()), int(iata_to_ints))
        self.flight_shortname = f'{self.flight_id[:3].upper()}_{self.origin_airport_iata}-{self.destination_airport_iata}_{self.departure_time.strftime("%y%m%d%H%M")}'
        self.flight_description = f'{self.flight_type} flight from {self.origin_airport_name} in {self.origin_municipality}{", " + self.origin_country if self.is_international else ""} to {self.destination_airport_name} in {self.destination_municipality}{", " + self.destination_country if self.is_international else ""}, departing on {self.departure_time.strftime("%d.%m.%y")} at {self.departure_time.strftime("%H:%M")} with {self.passenger_count} passengers.'      
        



flight = Flight({'origin_airport_iata': 'LAX', 'destination_airport_iata': 'CDG', 'passengers': {'first_class': 11, 'business_class:': 23, 'economy_class': 109}, 'departure_time': datetime(2020, 4, 26, 21, 53, 00, 000), 'arrival_time': datetime(2020, 4, 27, 4, 53, 00, 000)})

print(flight.flight_shortname)
print(flight.flight_description)
print(flight.flight_id)
