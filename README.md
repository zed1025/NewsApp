# NewsApp
News app, that uses data from Guardian API


## Android SDK Info
minSdkVersion 16

targetSdkVersion 26


## Libraries and API
* Volley
* RecyclerClick
* [Guardian API](http://open-platform.theguardian.com)

## Introduction
The App has 5 sections. 
* Current News
* Sports News
* Business News
* Lifestyle News
* Archive Section

The first four sections are self explanatory. The Archive section allows to do a more advanced search into the Guardian API Database.

## Implementation Details
* Splash Screen is set as the Launcher Activity, which after 2000ms takes you to the MainActivity
* MainActivity has a custom Toolbar, a FrameLayout and a BottomNavigationButton
* Each item in the BottomNavigation loada a fragment for a Section of News
* The image logo on the Toolbar clears the fragment stack and takes you to the CurrentNews Fragment
* News data are set to be fetched accorfing to the date

## Screenshots
![splash screen](https://github.com/zed1025/NewsApp/blob/master/ss1.png)
![image 1](https://github.com/zed1025/NewsApp/blob/master/ss2.png)
![image 2](https://github.com/zed1025/NewsApp/blob/master/ss3.png)
![image 3](https://github.com/zed1025/NewsApp/blob/master/ss4.png)
![web view](https://github.com/zed1025/NewsApp/blob/master/ss5.png)
