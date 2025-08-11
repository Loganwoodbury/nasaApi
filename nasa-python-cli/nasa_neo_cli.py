import requests
import sys
import datetime

#Put my api key here
API_KEY = "qY8mIG0nTahZRRvaeqkmIOe3bTmPkhWvwebYswrj"

#Define url for nasa Neo api
NEO_API_URL = "https://api.nasa.gov/neo/rest/v1/feed"

def main():

    if API_KEY == "YOUR_API_KEY_HERE" or not API_KEY:
        print("Please replace 'YOUR_API_KEY_HERE' with your actual key")
        sys.exit(1)

    date_str = input("Enter start date (YYYY-MM-DD): ")
    start_date = datetime.datetime.strptime(date_str, "%Y-%m-%d")
    end_date = start_date

    params = {
        "api_key" : API_KEY,
        "start_date" : start_date,
        "end_date" : end_date
    }

    try :
        print(f"\nFetching Near Earth Objects from {start_date} to {end_date}")
        response = requests.get(NEO_API_URL, params=params)

        response.raise_for_status()

        data = response.json()

        #print(data)

        # parse through the json data
        near_earth_objects = data['near_earth_objects']
        total_neo_count = data['element_count']

        print(f"Total Near Earth Objects found: {total_neo_count}")
        print("=" * 50)

        #for loop to loop through the neo's
        for date, neos_on_date in near_earth_objects.items():
            print(f"\n---Objects for {date} ({len(neos_on_date)} found) ---")
            for neo in neos_on_date:
                name = neo.get('name', 'N/A')
                neo_id = neo.get('id', 'N/A')
                is_potentially_hazardous = neo.get('is_potentially_hazardous_asteroid', False)

                estimated_diameter_meters = neo['estimated_diameter']['meters']
                min_diameter = estimated_diameter_meters['estimated_diameter_min']
                max_diameter = estimated_diameter_meters['estimated_diameter_max']

                print(f"Name: {name}")
                print(f"Id: {neo_id}")
                print(f"Potentially Hazardous: {is_potentially_hazardous}")
                print(f"Estimated Diameter (meters: {min_diameter} to {max_diameter})")

    except requests.exceptions.RequestException as e:
        print(f"An exception occurred: {e}")

if __name__ == "__main__":
    main()
        