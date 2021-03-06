# Coronafeed

<img src="https://media1.giphy.com/media/gkXSfmA8ynT59X9rr4/giphy.gif?cid=ecf05e4791af4b03f65e8622147f71e2ef433050d41d8ddd&rid=giphy.gif" width="250" height="250" />

An android app for all your Corona news related needs

## Purpose
As of writing this, the coronavirus has infected over two million people. The United States is leading with the world's most cases at 700,000. Almost daily, the newscycle is flooded with new developments on this virus. It is for that reason important as ever to be informed about the disease. This app connects to an RSS feed of Google News to give the user a live feed of news articles regarding the virus. 
## Features
1. **Recycler View(s)**: Recycler views display articles. On the home page, these articles are pulled directly from the RSS feed daily, whereas, the "read later" recycler view stores articles the user selected to read later.
2. **Dialogs and Toasts**: These dialogs are triggered each time you click on an article in any of the recycler views. Users have the option to view the article online, put it in the read later list, or exit the dialog. Toast messages are used to show the user when an article has been added or removed from read later.
3. **A Fragment Navbar**: A navbar at the bottom gives the user the option to switch between the home page and the read later page. The navbar does this by switching through fragments for each. 
4. **Intents**: An action intent is used to launch the user from the app to their browser to view a particular article.
5. **SQLite Database w/ Android Architecture**: The read later page is backed by a SQLite database. I didn't feel like going through the pain of doing SQL the old fashioned way so I was inspired by Coding In Flow's [tutorial series](https://www.youtube.com/watch?v=ARpn-1FPNE4) on Android Architecture components. He does a way better job of explaining how this works, but to paraphrase what was said, the basic structure works a little like this. 
      - Room: Android's own SQLite wrapper that takes care of most of the heavy lifting when it comes to the database.
      - DAO (Data Acess Object): This is technically part of room but is important to mention nonetheless.  DAO's communicate directly with SQLite and is where basic operations-- insert, delete, update-- are first initialized.
      - Repository: Not necessarily from android architecture but a useful class to access data from the DAO to use in the ViewModel. Recommended as it adds scalability as it can mediate from different data services. Creates an API for that the ViewModel can connect to.
      - View Model: Holds the data used for the user interface. Used so that we dont have to put data directly into the acitivities / fragments, which would otherwise cause data leaks. This also has the added benefit of retaining data after conformation changes e.g: rotating the screen for us. Finally, this keeps a lot of code out of our activities that would otherwise clutter it.

All throughout this LiveData is used to bubble data directly from the database to the recyclerview by using observers that update the db as soon as a change is detected like if we were to add a new article to it.

![Coding In Flow diagram](https://i.imgur.com/hzq9OXs.png)

Here's a graphic straight from the video that visualizes the whole process. It's very neat!

6. **And the very best feature:** A dark theme :shipit:

## Demo
<img src="https://i.imgur.com/mzD6mg9.gif" width="250" height="500" />




