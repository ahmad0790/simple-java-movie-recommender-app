import java.io.*;
import java.util.*;
import java.io.FileWriter;

public class ChildUser extends User {
    //private int userAge;
    //private MovieLibrary allMovies = new MovieLibrary();
    //private HashMap<String, MovieInfo> myMovieShop = new HashMap<>();
    //private ArrayList<String> completeMovieList = new ArrayList<String>();
    
    public ChildUser() throws IOException{
       this.generateUserId();
       this.setMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
       this.removeInappriopriateMovies();
    }
    
    public ChildUser(String n, int a) throws IOException{
        this.generateUserId();
        this.setUserAge(a);
        this.setUserName(n);
        setMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
        //allMovies.createMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
        //myMovieShop = allMovies.getMovieLibrary();
        //completeMovieList = allMovies.getAllMovies();
        this.removeInappriopriateMovies();
        //this.setMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
    }
    
    public static void main(String [] args) throws IOException{
        //User newUser = new User("Ahmad", 15);
        //User newUser2 = new User("Adam", 26);
        User newUser = new ChildUser("Young Ahmad", 15);
        System.out.println(newUser.getUserId());
        //System.out.println(newUser2.getUserId());
        //System.out.println(newKid.getUserId());

        System.out.println(newUser.getRemainingMovies());
        System.out.println(newUser.getRemainingMovieListSize());
        
        System.out.println(newUser.getRemainingMovies());
        System.out.println(newUser.getRemainingMovieListSize());
        newUser.addMovie("Avatar","Watched");
        newUser.addMovie("The Dark Knight Rises","Watched");
        newUser.addMovie("Man of Steel","Watched");
        newUser.addMovie("Titanic","Watched");
        newUser.addMovie("Junk Movie","Watched");
        newUser.addMovie("Toy Story 3","Watched");
        newUser.addMovie("The Avengers","Watched");
        newUser.addMovie("Transformers","Watched");
        newUser.addMovie("Pirates of the Caribbean","Watched");
        newUser.addMovie("Zero Dark Thirty","Watched");
        newUser.addMovie("Inception","Watched");
        
        //newUser.addMovie("The Avengers","Liked");
        newUser.addMovie("Jurassic World","Liked");
        newUser.addMovie("Skyfall","Liked");
        System.out.println(newUser.getRemainingMovieListSize());
        
        System.out.println(newUser.getMoviesWatched());
        System.out.println(newUser.getMoviesLiked());
        
        System.out.println(newUser.getWatchedCounts("Director"));
        System.out.println(newUser.getWatchedCounts("Actor"));
        System.out.println("Your most common Director is " + newUser.getMostFrequentDirector());
        
        System.out.println(newUser.searchMovies("Johnny Depp","All"));
        System.out.println(newUser.searchMovies("James Cameron","All"));
        System.out.println(newUser.searchMovies("2013","All"));
        System.out.println(newUser.searchMovies("James Cameron","Leonardo DiCaprio", "Director","Actor","All"));
        System.out.println(newUser.searchMovies("James Cameron","Leonardo DiCaprio", "Director","Actor","Watched"));
        System.out.println(newUser.searchMovies("Matt Damon", "Action","Actor","Genre","All"));
        
        newUser.deleteMovieWatched("Avatar");
        System.out.println(newUser.getMoviesWatched());
        System.out.println(newUser.getWatchedCounts("Director"));
        System.out.println(newUser.getWatchedCounts("Actor"));
        System.out.println("Your most common Director is " + newUser.getMostFrequentDirector());
        System.out.println("Your most common Year is " + newUser.getMostFrequentYear());
        
        //System.out.println("Here are some movies for you " + Arrays.toString(newUser.getNrandomMovies(5)));
        String [][] arr = newUser.getTopMovies("All", 5);
        for(int i=0; i< arr.length; i++){
            System.out.println(arr[i][0]);
            System.out.println(arr[i][1]);
        }
        
        System.out.println(Arrays.toString(newUser.getRecommendedMovies(12)));
        newUser.clearMoviesWatchedList();
        System.out.println(newUser.getMoviesWatched());
    }
    
    public void removeInappriopriateMovies(){              
        ArrayList <String> ratedRMovieList = new ArrayList<String>();
        for (String key : getRemainingMoviesLibrary().keySet()){
            if (getRemainingMoviesLibrary().get(key).getContentRating().equals("R")){
                ratedRMovieList.add(key);
            }
        }
        for (String key : ratedRMovieList){
            deleteMovieFromLibrary(key, getRemainingMovies(), getRemainingMoviesLibrary());    
        }
    }
}
