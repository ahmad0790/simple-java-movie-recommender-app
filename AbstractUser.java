import java.io.*;
import java.util.*;

public interface AbstractUser {
     
    //methods to get and set values
    public void generateUserId();
      
    public void setUserName(String name);
        

    public void setUserAge(int age);
        
    public void removeInappriopriateMovies();
    
    public void setMovieLibrary(String filename) throws IOException;    
       
    public void addMovie(String m, String libraryChoice);
        
    //public static void deleteMovieFromLibrary(String movie, ArrayList<String> movieList, HashMap<String, MovieInfo> movieLibrary);
        
    public void updateRemainingMoviesList(String movie);
        
    public void deleteMovieWatched(String movie);
       
    public void deleteMovieLiked(String movie);
      
    public void clearMoviesWatchedList();
    
    public void clearMoviesLikedList();
      
    public String getUserName();
      
    public int getUserAge();
     
       
    public int getUserId();
    
    public HashMap<String, MovieInfo> getRemainingMoviesLibrary();
     
    //public ArrayList<String> getRemainingMovieList();
    
    public int getRemainingMovieListSize();
       
    public ArrayList<String> getMoviesWatched();
        
     
    public ArrayList<String> getMoviesLiked();
    
    //public static HashMap<String, Integer> getItemCounts(ArrayList<String> lst);
     
    
    //public static String[] getMax(HashMap<String, Integer> freqHashMap);
    
    public HashMap<String, Integer> getWatchedCounts(String fieldType);

    public String [] getFavorite(String fieldType);

    public String getMostFrequentDirector();
    
    public String getMostFrequentActor();

    public String getMostFrequentGenre();

    public String getMostFrequentYear();

    public String getMostFrequentDirectorValue();

    public String getMostFrequentActorValue();
    
    public String getMostFrequentGenreValue();

    public String getMostFrequentYearValue();

    public ArrayList<String> searchMovies(String field, String searchScope);

    public ArrayList<String> searchMovies(String field, String fieldTwo, String fieldType, String fieldTypeTwo, String SearchScope);

    public ArrayList <String> filterMoviesByRating(HashMap<String, MovieInfo> library, double ratingCutoff, int n);

    //public static String[][] findTopMovies(HashMap<String, MovieInfo> library, int n);

    public String[][] getTopMovies(String returnScope, int movieLimit);

    //public static double[] getAverages(HashMap<String, MovieInfo> library);

    public String [] getNrandomMovies(int n);

    public String [] getRecommendedMovies(int n);
    
    public void saveMoviesWatched(String filepathname) throws IOException;
}
