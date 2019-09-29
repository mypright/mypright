# MyPright

Use MyPright to take the true ownership of your personal data. 
Refrain third party login services (you know who. Isshhh !! they are 
tracking you !) who target you with your data.

With MyPright, you decide who should have access your data and until when. 
Centralized control of all your data. 
Grant/Revoke the access to any service when ever you want just with a click.

And guess what ? 
Even MyPright doesn't store your data. 
Your store your data in your private instance by deploying 
it into your private cloud.

![alt text](flow_diagram.png "Flow Diagram")

In simple words, it is :
#### Your private single sign-on and a private personal data vault.

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

