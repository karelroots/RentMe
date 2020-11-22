from locust import HttpUser, task, between
from requests import urllib3


# To run against local env: "locust -f rentme.py --host=http://localhost:8080"


class WebsiteUser(HttpUser):
    wait_time = between(1, 1)

    def on_start(self):
        urllib3.disable_warnings()  # disable SSL warnings

    def get(self, link):
        self.client.get(link, verify=False)

    def post(self, link, data):
        self.client.post(link, data, verify=False)

    @task(1)
    def index(self):
        self.get('/')

    @task(1)
    def login(self):
        self.post('/login', {"email": "test@test.ee", "password": "test", "Submit": "Login"})
