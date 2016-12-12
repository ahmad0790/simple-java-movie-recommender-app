import java.util.Scanner;
import java.util.*;
import java.io.*;

public class NextflickMain
{
    public static void main(String[] args) throws IOException {
        boolean programRunning = true;
        //this abstract class list adds all the users who have a Nextflick account
        ArrayList<AbstractUser> userList = new ArrayList<AbstractUser>();
        while (programRunning = true) {
            boolean loggedIn = false;
            boolean isValidNewAccount = true;
            int newOldUserAnswer;
            AbstractUser newUser = new User();
            
            System.out.println("Welcome to Nextflick! The movie database and recommender of the future!");
            System.out.println("-----------------------------------------------------------------------");
            
            while (loggedIn == false){
                isValidNewAccount = true;
                Scanner keyboard = new Scanner(System.in);
                System.out.println("");
                System.out.println("Press 1 to log into an existing account if you are a returning user. Press 2 to create a new account if you are a new user");
                System.out.println("Press 3 to see which users are currently online as well");
                newOldUserAnswer = keyboard.nextInt();

                if (newOldUserAnswer == 1){
                    System.out.println("Welcome Back! Please enter your age, then hit enter, and then enter your name to log in");
                    int userAge;
                    String userName;
                    boolean userFound = false;
                    //int userListIndex;
                    
                    userAge = keyboard.nextInt(); 
                    userName = keyboard.next();
                    System.out.println("Hello " + userName);

                    if(userAge >= 18){
                        newUser = new User(userName, userAge);
                        int i = 0;
                        for (AbstractUser user : userList){
                            if (newUser.equals(user)){
                                isValidNewAccount = true;
                                userFound = true;
                                newUser = userList.get(i);
                                break;
                            }
                            i = i + 1;
                        }
                        if (userFound == true){
                            loggedIn = true;
                            System.out.println("Welcome Back " + userName + "!");
                        }
                        
                        else if(userFound == false){
                            loggedIn = false;
                            System.out.println("Are you sure you are a returning user? We could not find any account associated with your User Name and Age :(");
                            System.out.println("Please try again making sure you spelled your name and age correctly");
                        }
                    }
                    else if (userAge >= 1 && userAge < 18){
                        newUser = new ChildUser(userName, userAge);
                        int i = 0;
                        for (AbstractUser user : userList){
                            if (newUser.equals(user)){
                                isValidNewAccount = true;
                                userFound = true;
                                newUser = userList.get(i);
                                break;
                            }
                            i = i + 1;
                        }
                        
                        if (userFound == true){
                            loggedIn = true;
                            System.out.println("Welcome Back " + userName + "!");
                        }
                        
                        else if(userFound == false){
                            loggedIn = false;
                            System.out.println("Are you sure you are a returning user? We could not find any account associated with your User Name and Age :(");
                            System.out.println("Please try again making sure you spelled your name and age correctly");
                        }
                    }
                }
                
                else if (newOldUserAnswer == 2){
                    System.out.println("Let's get started. Please enter your age, then hit enter, and then enter your name to create an account");
                    int age;
                    String name;
                    age = keyboard.nextInt(); 
                    name = keyboard.next();
                    System.out.println("Hello " + name);

                    if(age >= 18){
                        newUser = new User();
                        newUser.setUserName(name);
                        newUser.setUserAge(age);
                        for (AbstractUser user : userList){
                            if (newUser.equals(user)){
                                System.out.println("Sorry but an account with that username already exists. Please try again by typing 1 to log in");
                                isValidNewAccount = false;
                                newUser = null;
                                break;
                            }
                        }
                        
                        if (isValidNewAccount == true){ 
                            userList.add(newUser);
                            System.out.println("Your account has been successfully created");
                            loggedIn = true;
                        }
                    }
                    else if (age >= 1 && age < 18){
                        newUser = new ChildUser();
                        newUser.setUserName(name);
                        newUser.setUserAge(age);
                        for (AbstractUser user : userList){
                            if (newUser.equals(user)){
                                System.out.println("Sorry but an account with that name and age already exists. Please type 1 to log in");
                                isValidNewAccount = false;
                                break;
                            }
                        }
                        
                        if (isValidNewAccount == true){ 
                            userList.add(newUser);
                            System.out.println("Your account has been successfully created");
                            loggedIn = true;
                        }
                    }   
                }
                
                else if (newOldUserAnswer == 3){
                    System.out.println("It seems like there are " + userList.size() + " users online. Are you one of them?");
                }
                
                else{
                    //Scanner keyboard = new Scanner(System.in);
                    System.out.println("Press enter a valid number. 1 for Existing User. 2 for Returning User. 3 to see users online");
                }
            }
            //opening a user's account now that they have logged in
            int userChoice = 0;
            System.out.println("You have successfully logged in!");
            System.out.println("Here is the user list of all users currently online in the system" + userList);
            while (loggedIn = true && isValidNewAccount == true && userChoice != -1){
                System.out.println("What would you like to do?");
                System.out.println("You have " + newUser.getRemainingMovieListSize() + " movies available at your disposal. Wow, isn't that great!");
                System.out.println("-----------------------------------------------------------");
                System.out.println(" ");
                System.out.println("Press 1 to add a movie you've watched to your Nextflick account");
                System.out.println("Press 2 to add a movie you want to see to your Nextflick wishlist");
                System.out.println("Press 3 to delete a movie you've watched from your personal Nextflick account");
                System.out.println("Press 4 to delete a movie from your Nextflick wishlist");
                System.out.println("Press 5 to see the movies you have watched.");
                System.out.println("Press 6 to see the movies you have in your wishlist");
                System.out.println("Press 7 to see your favorite actors, genres or directors.");
                System.out.println("Press 8 to see highest rated movies from the entire Nextflick database.");
                System.out.println("Press 9 to search for movies in Nextflick database.");
                System.out.println("Press 10 to see recommended movies just for you");
                System.out.println("Press 11 to see which actors, genres or directors you've seen movies");
                System.out.println("Press 12 for additional actions that you can take");
                System.out.println("Press -1 at any time to exit.");
                System.out.println("-----------------------------------------------------------");
                
                Scanner keyboard = new Scanner(System.in);
                userChoice = keyboard.nextInt();
                
                switch (userChoice)
                {
                    case 1:
                        System.out.println("Please enter the title of the movie you have watched");
                        System.out.println("You may continue to add movies until you enter STOP");
                        String newMovie = keyboard.nextLine();
                        while(!newMovie.equalsIgnoreCase("STOP"))
                        {
                            newMovie = keyboard.nextLine();
                            newUser.addMovie(newMovie, "Watched");
                            System.out.println("Please enter another movie name or STOP to go back");
                        }
                        break;

                    case 2:
                    
                        System.out.println("Please enter the title of the movie you want to add to your wishlist");
                        System.out.println("You may continue to add movies until you enter STOP");
                        String newMovieLiked = keyboard.nextLine();
                        while(!newMovieLiked.equalsIgnoreCase("STOP"))
                        {
                            newMovieLiked = keyboard.nextLine();
                            newUser.addMovie(newMovieLiked, "Liked");
                            System.out.println("Please enter another movie name or STOP to go back");
                        }
                        break;

                    case 3:
                    
                        System.out.println("Please enter the title of the movie you want to delete from your watch-list");
                        System.out.println("You may continue to delete movies until you enter STOP");
                        String movieToDeleteWatched = "placeholder";
                            
                        while(!movieToDeleteWatched.equalsIgnoreCase("STOP"))
                        {
                            movieToDeleteWatched = keyboard.nextLine();
                            newUser.deleteMovieWatched(movieToDeleteWatched);
                            System.out.println("Please enter another movie to delete or STOP to go back");
                        }
                        break;
                        
                    case 4:
                    
                        System.out.println("Please enter the title of the movie you want to delete from your wish-list");
                        System.out.println("You may continue to delete movies until you enter STOP");
                        String movieToDeleteLiked = "placeholder";
       
                        while(!movieToDeleteLiked.equalsIgnoreCase("STOP"))
                        {
                            movieToDeleteLiked = keyboard.nextLine();
                            newUser.deleteMovieLiked(movieToDeleteLiked);
                            System.out.println("Please enter another movie to delete or STOP to go back");
                        }
                        break;
                    
                    case 5:
                        System.out.println("These are the movies you have seen so far");
                        System.out.println(newUser.getMoviesWatched());
                        break;
                    
                    case 6:
                        System.out.println("These are the movies that you want to see in your wish list");
                        System.out.println(newUser.getMoviesLiked());
                        break;
                        
                    case 7:
                        System.out.println("Here are your favorites based on your viewing history");
                        System.out.println("Your most watched director is " + newUser.getMostFrequentDirector() + " and you have seen " + newUser.getMostFrequentDirectorValue()  + " movies by them");
                        System.out.println("Your most watched person is " + newUser.getMostFrequentActor() + " and you have seen " + newUser.getMostFrequentActorValue()  + " movies by them");
                        System.out.println("Your most watched genre is " + newUser.getMostFrequentGenre() + " and you have seen " + newUser.getMostFrequentGenreValue()  + " movies in this genre");
                        System.out.println("Your most watched year is " + newUser.getMostFrequentYear() + "and you have seen " + newUser.getMostFrequentYearValue()  + " movies in this year");
                        break;
        
                    case 8:
                        System.out.println("Which movie list do you want to find top movies for? Type 1 for All, Type 2 for your Watched Movies, Type 3 for your Liked Movies");
                        //Scanner keyboard = new Scanner(System.in);
                        int sortList = keyboard.nextInt();
                        System.out.println("How many movies do you want to return? You must put in an integer greater than 1 and less than the size of the movie list you are referencing");
                        int moviesToShow = keyboard.nextInt();
                        System.out.println("Here are the top movies based on your list...");
                        
                        if (sortList == 1){
                            String [][] arr = newUser.getTopMovies("All", moviesToShow);
                            for(int i=0; i< arr.length; i++){
                                System.out.println(arr[i][0]);
                                System.out.println("with a Rating of " + arr[i][1]);
                            }
                        }
                        else if (sortList == 2){
                            String [][] arr = newUser.getTopMovies("Watched", moviesToShow);
                            for(int i=0; i< arr.length; i++){
                                System.out.println(arr[i][0]);
                                System.out.println(" with a Rating of " + arr[i][1]);
                            }
                        }
                        else if (sortList == 3){
                            String [][] arr = newUser.getTopMovies("Liked", moviesToShow);
                            for(int i=0; i< arr.length; i++){
                                System.out.println(arr[i][0]);
                                System.out.print(" with a Rating of " + arr[i][1]);
                            }
                        }
                        break;
                    
                    case 9:
                        System.out.println("Where do you want to search? Press 1 for All movies, Press 2 for Watched Movies or Press 3 for Wish List?");
                        int searchLibrary = keyboard.nextInt();
                        System.out.println("What would you like to search for? You can search for a director, actor, genre, year of a movie");
                        Scanner search = new Scanner(System.in);
                        String searchQuery = search.nextLine();
                
                        
                        if (searchLibrary == 1){
                            System.out.println(newUser.searchMovies(searchQuery, "All"));
                        }
                        else if (searchLibrary == 2){
                             System.out.println(newUser.searchMovies(searchQuery, "Watched"));
                        }
                        else if (searchLibrary == 3){
                             System.out.println(newUser.searchMovies(searchQuery, "Liked"));
                        }
                        break;
                        
                    case 10:
                        System.out.println("What Kind of recommendations do you want?. Press 1 for Random Recommendation, Press 2 for Personalized Recommendation");
                        //Scanner keyboard = new Scanner(System.in);
                        int recommendationType = keyboard.nextInt();
                        System.out.println("How many movies do you want returned? We recommend 10 or less movies but more than 1");
                        int recommendationLength = keyboard.nextInt();
                        
                        if (recommendationType == 1){
                            System.out.println("Here are some movies for you " + Arrays.toString(newUser.getNrandomMovies(recommendationLength)));
                        }
                        else if (recommendationType == 2){
                            System.out.println("Here are some movies for you " + Arrays.toString(newUser.getRecommendedMovies(recommendationLength)));
                        }
                        break;
                     
                    case 11:
                        System.out.println("For which do you want to see frequencies for? Press 1 for Actor, 2 for Director, 3 for Genre, 4 for Year?");
                        //Scanner keyboard = new Scanner(System.in);
                        int freqCriteria = keyboard.nextInt();
                        
                        if (freqCriteria == 1){
                            System.out.println(newUser.getWatchedCounts("Actor"));
                        }
                        else if (freqCriteria == 2){
                            System.out.println(newUser.getWatchedCounts("Director"));
                        }
                        else if (freqCriteria == 3){
                            System.out.println(newUser.getWatchedCounts("Genre"));
                        }
                        else if (freqCriteria == 4){
                            System.out.println(newUser.getWatchedCounts("Year"));
                        }
                        break;
                    
                    case 12:
                        System.out.println("What other stuff would you like to do?");
                        System.out.println("Press 1 - To get the number of remaining movies avalilable for you");
                        System.out.println("Press 2 - To get a complete list of movies available and their attributes");
                        System.out.println("Press 3 - To clear your movies watched list");
                        System.out.println("Press 4 - To clear your movies liked list");
                        System.out.println("Press 5 - To save the movies your wish list on your computer");
                        int additionalStuff = keyboard.nextInt();
                        
                        if (additionalStuff == 1){
                            System.out.println("There are " + newUser.getRemainingMovieListSize() + " remaining movies in the Nextflick database");
                        }
                        else if (additionalStuff == 2){
                            System.out.println("There are " + newUser.getRemainingMoviesLibrary() + " remaining movies in the Nextflick database");
                        }
                        else if (additionalStuff == 3){
                            newUser.clearMoviesWatchedList();
                            System.out.println("Your watched movie list has been successfully cleared and is now empty");
                        }
                        
                        else if (additionalStuff == 4){
                            newUser.clearMoviesLikedList();
                            System.out.println("Your movie wish-list has been successfully cleared and is now empty");
                        }
                        
                        else if (additionalStuff == 5){
                            newUser.saveMoviesWatched("/Users/ahkhan/Downloads/wishlist.csv");
                            System.out.println("Your movie wish list has been saved");
                        }
                        
                        else {
                            System.out.println("Please enter a valid number");
                        }
                        break;
                     
                    case -1:
                        loggedIn = false;
                        System.out.println("Goodbye " + newUser.getUserName()+ "!We hope to see you again!");
                        break;
                }
            }
            System.out.println(" ");
            System.out.println("Would another user like to log in? Press 1 for Yes, Press 2 for No to exit the program");
            Scanner keyboard = new Scanner(System.in);
            int shouldContinue = keyboard.nextInt();
            if (shouldContinue == 2){
                System.out.println("Goodbye! Please come back to Nextflick again");
                System.out.println("------SUCCESSFULLY EXITED NEXTFLICK-----------");
                programRunning = false;
                break;
            }  
        }
    }
}
