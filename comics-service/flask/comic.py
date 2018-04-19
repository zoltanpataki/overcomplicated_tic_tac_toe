from flask import Flask
import requests
import random
import json
app = Flask(__name__)

@app.route("/getcomic")
def get_comic():
    basic_url = "https://xkcd.com/"
    additional_number = str(random.randint(1, 1500))
    comic_url = basic_url + additional_number + "/info.0.json"
    data = requests.get(comic_url).content
    decoded_data = json.loads(data.decode("utf-8"))
    return decoded_data["img"]


if __name__ == "__main__":
    app.secret_key = "whoeventriestoguessthis"
    app.run(
        debug=True,
        port=60007
    )