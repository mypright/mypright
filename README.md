# MyPright

Your private signle signon and a private vault.

## Running Locally

Make sure you have Java, Gradle setup locally.

```sh
git clone git@gitlab.com:mypright/mypright.git # or clone your own fork
cd mypright
./gradlew clean build
./gradlew bootRun
```

Your app should now be running on [localhost:8080](http://localhost:8080/).

## Deploying to Heroku

```
heroku create
git push heroku master
heroku open
```

Alternatively, you can deploy your own copy of the app using the web-based flow:

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy?template=https://github.com/mypright/mypright)

## Documentation
