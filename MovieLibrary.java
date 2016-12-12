import java.io.*;
import java.util.*;

public class MovieLibrary {
    private HashMap<String, MovieInfo> Movies = new HashMap<>();
    private boolean rowsSkipped = false;
    
    public MovieLibrary(String filename) throws IOException{
        this.createMovieLibrary(filename);
        this.rowsSkipped = this.getRowsNotRead() ;
    }
    
    public MovieLibrary(){
         HashMap<String, MovieInfo> Movies = new HashMap<>();
         rowsSkipped = false;
   }
    
    public void createMovieLibrary(String filename) throws IOException {
        String csvFile = filename;
        String line = "";
        String csvSplitBy = ",";
        BufferedReader movieData = new BufferedReader(new FileReader(new File(csvFile)));
        HashMap<String, MovieInfo> movieHashMap = new HashMap<>();
        int iteration = 0;
        //MovieInfo(String mDir, int mDur, String ac2, String mGen, String ac1, String mTitl, String ac3, int mYear, double iScore){
        //'director_name' 'duration' 'actor_2_name' 'genres' 'actor_1_name' 'movie_title' 'actor_3_name' 'title_year' 'imdb_score'
        while ((line = movieData.readLine()) != null) { //read each line until there are no more lines to read!!!
            try{
                if(iteration == 0) {
                    iteration++;  
                    continue;   
                }
                else
                {
                    String [] movie = line.split(csvSplitBy); 
                    String movieTitle = movie[4].trim();
                    movieTitle = movieTitle.replaceAll("[^A-Za-z0-9\\s]", "");

                    MovieInfo newMovie = new MovieInfo(movie[0], Double.parseDouble(movie[1]), movie[2], movie[3], movieTitle, movie[5],  movie[6], Integer.parseInt(movie[7]), Double.parseDouble(movie[8])
                                                        ,movie[9], movie[10], movie[11]);
                    movieHashMap.put(movieTitle, newMovie);
                    //System.out.println(movie[4]);
                    //System.out.println(movieHashMap.get(movie[4]).getMovieTitle());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                rowsSkipped = true;
                //return null;
            }
        }
        this.Movies = movieHashMap; 
        //return Movies;
    }
        
    public HashMap<String, MovieInfo> getMovieLibrary(){
        return this.Movies;   
    }
    
    public ArrayList<String> getAllMovies() {
        ArrayList <String> movieList = new ArrayList<String>();
        for (String key : Movies.keySet()){
            movieList.add(this.getMovieLibrary().get(key).getMovieTitle());
        }
        return movieList;
    }
    
    public boolean getRowsNotRead(){
        return rowsSkipped; 
    }
    
    public int getRowsRead(){
        return this.Movies.size(); 
    }
}
