import java.io.*;
import java.util.*;
import java.io.FileWriter;

public class User implements AbstractUser{
   //initializing user variables
    private static int userId = 0;
    private int userAge;
    private String userName;
    
    //initializing movie library
    private MovieLibrary allMovies = new MovieLibrary();    
    private MovieLibrary allMoviesCopy = new MovieLibrary();    
    //initializing movies liked and watched in a hashmap
    private HashMap<String, MovieInfo> originalMovieLibrary = new HashMap<>();
    private HashMap<String, MovieInfo> myMovieShop = new HashMap<>();
    private HashMap<String, MovieInfo> moviesWatchedLibrary = new HashMap<>();
    private HashMap<String, MovieInfo> moviesLikedLibrary = new HashMap<>();
    
    //initializing useful arrayLists to return and use in other methods
    private ArrayList<String> moviesWatched = new ArrayList<String>();
    private ArrayList<String> moviesLiked = new ArrayList<String>();
    private ArrayList<String> completeMovieList = new ArrayList<String>();
    private ArrayList<String> originalMovieList = new ArrayList<String>();
    
    //constructors
    public User() throws IOException{
        this.generateUserId();
        this.setMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
        //this.allMovies.createMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
        //this.myMovieShop = this.allMovies.getMovieLibrary();
        //this.completeMovieList = this.allMovies.getAllMovies();
    }
    
    public User(String n, int a) throws IOException{
        //userId = userId + 1;
        this.generateUserId();
        this.userAge  = a;
        this.userName = n;
        this.setMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
        //this.allMovies.createMovieLibrary("/Users/ahkhan/Downloads/movies2.csv");
        //this.myMovieShop = this.allMovies.getMovieLibrary();
        //this.completeMovieList = this.allMovies.getAllMovies();
    }
    
    //testing
    public static void main (String [] args) throws IOException{
        User newUser = new User();
        newUser.setUserName("Ahmad");
        newUser.setUserAge(26);
        System.out.println("-----NEW RUN-----");
        System.out.println("User 1 your User Id is " + newUser.getUserId()) ;
        User newUser2 = new User("SomeoneElse", 23);
        System.out.println("User 2 your User Id is " + newUser2.getUserId()) ;
        
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
        newUser.saveMoviesWatched("/Users/ahkhan/Downloads/watched.csv");
        
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
        
        System.out.println("Here are some movies for you " + Arrays.toString(newUser.getNrandomMovies(5)));
        String [][] arr = newUser.getTopMovies("All", 5);
        for(int i=0; i< arr.length; i++){
            System.out.println(arr[i][0]);
            System.out.println(arr[i][1]);
        }
        
        System.out.println(Arrays.toString(newUser.getRecommendedMovies(12)));
        //System.out.println(newUser.filterMoviesByRating(newUser.myMovieShop, 8, 20));
        
        newUser.clearMoviesWatchedList();
        System.out.println(newUser.getMoviesWatched());
        User newUser3 = new User("Adam", 25);
        User newUser4 = new User("Adam", 24);
        System.out.println(newUser3.equals(newUser4));
        System.out.println(newUser3);
        System.out.println(newUser4);
    }
    
    //methods to get and set values
    public void generateUserId(){
        userId = userId + 1;  
    }
    
    public void setUserName(String name){
        this.userName = name;
    }

    public void setUserAge(int age){
        this.userAge = age;
    }
    
    public void setMovieLibrary(String filename) throws IOException{
        this.allMovies.createMovieLibrary(filename);
        this.myMovieShop = this.allMovies.getMovieLibrary();
        this.completeMovieList = this.allMovies.getAllMovies();
        //also creating a backup of the movie list and movie library since we will be deleting movies from the myMovieShop library
        this.allMoviesCopy.createMovieLibrary(filename);
        this.originalMovieLibrary = this.allMoviesCopy.getMovieLibrary();
        this.originalMovieList = this.allMoviesCopy.getAllMovies();
    }
    
    public void removeInappriopriateMovies(){ 
        System.out.println("You are an Adult...seems like you can watch anything that you want");
    }
       
    public void addMovie(String m, String libraryChoice){
        if (this.completeMovieList.contains(m)){
            //create a library copy of only movies you have watched. Input should be in the format
            //'director_name' 'duration' 'actor_2_name' 'genres' 'actor_1_name' 'movie_title' 'actor_3_name' 'title_year' 'imdb_score'
            MovieInfo newMovie = new MovieInfo(
              myMovieShop.get(m).getMovieDirector()
            , myMovieShop.get(m).getMovieDuration()
            , myMovieShop.get(m).getActorTwo()
            , myMovieShop.get(m).getActorOne()
            , myMovieShop.get(m).getMovieTitle()
            , myMovieShop.get(m).getContentRating()
            , myMovieShop.get(m).getActorThree()
            , myMovieShop.get(m).getMovieYear()
            , myMovieShop.get(m).getiMDBRating()
            , myMovieShop.get(m).getMovieGenre1()
            , myMovieShop.get(m).getMovieGenre2()
            , myMovieShop.get(m).getMovieGenre3()
            );
            
            if(libraryChoice.equals("Watched")){
                this.moviesWatchedLibrary.put(m, newMovie);
                System.out.println(m + " was successfully added to your Watched Movie Library");
            }
            else if(libraryChoice.equals("Liked")){
                this.moviesLikedLibrary.put(m, newMovie); 
                System.out.println(m + " was successfully added to your Wish-List Movie Library");
            }
     
            this.updateRemainingMoviesList(m);
           } 
        else {
            System.out.println("Please enter a valid Movie");
           } 
        
        //if a movie
        /*if (libraryChoice.equals("All") && this.originalMovieList.contains(m)){
            MovieInfo newMovie = new MovieInfo(
              originalMovieLibrary.get(m).getMovieDirector()
            , originalMovieLibrary.get(m).getMovieDuration()
            , originalMovieLibrary.get(m).getActorTwo()
            , originalMovieLibrary.get(m).getActorOne()
            , originalMovieLibrary.get(m).getMovieTitle()
            , originalMovieLibrary.get(m).getContentRating()
            , originalMovieLibrary.get(m).getActorThree()
            , originalMovieLibrary.get(m).getMovieYear()
            , originalMovieLibrary.get(m).getiMDBRating()
            , originalMovieLibrary.get(m).getMovieGenre1()
            , originalMovieLibrary.get(m).getMovieGenre2()
            , originalMovieLibrary.get(m).getMovieGenre3()
            );
            this.myMovieShop.put(m, newMovie); 
            this.completeMovieList.add(m);
        }*/
    }
    
    public static void deleteMovieFromLibrary(String movie, ArrayList<String> movieList, HashMap<String, MovieInfo> movieLibrary){
        for (String i : movieList) {
            if (i.contains(movie)){
                movieList.remove(i);
                movieLibrary.remove(i);
                break;
            }
        }   
    }
      
    public void updateRemainingMoviesList(String movie){
        deleteMovieFromLibrary(movie, this.completeMovieList, this.myMovieShop);
    }
    
    public void deleteMovieWatched(String movie){
        //addMovie(movie, "All");
        deleteMovieFromLibrary(movie, this.moviesWatched, this.moviesWatchedLibrary);
    }
      
    public void deleteMovieLiked(String movie){
        //addMovie(movie, "All");
        deleteMovieFromLibrary(movie, this.moviesLiked, this.moviesLikedLibrary);
    }
    
    public void clearMoviesWatchedList(){
       /*for (String m : moviesWatchedLibrary.keySet()){
           addMovie(m, "All");
       }*/
       this.moviesWatchedLibrary.clear();
    }
    
    public void clearMoviesLikedList(){
       /*for (String m : moviesWatchedLibrary.keySet()){
           addMovie(m, "All");
       }*/
        this.moviesLikedLibrary.clear() ;
    }
    
    //getter methods
    public String getUserName() {
        return userName;
    }
    
    public int getUserAge(){
        return userAge;
    }
       
    public int getUserId(){
        return userId;
    }
   
    public HashMap<String, MovieInfo> getRemainingMoviesLibrary(){
        return this.myMovieShop;
    }
        
    public ArrayList<String> getRemainingMovies(){
        return completeMovieList;
    }
    
    public int getRemainingMovieListSize(){
        return completeMovieList.size();
    }
      
    public HashMap<String, MovieInfo> getMoviesWatchedLibrary(){
        return this.moviesWatchedLibrary;
    }
    
    public HashMap<String, MovieInfo> getMoviesLikedLibrary(){
        return this.moviesLikedLibrary;
    }
    
    public ArrayList<String> getMoviesWatched(){
        moviesWatched.clear();
        for (String key : moviesWatchedLibrary.keySet()){
            moviesWatched.add(moviesWatchedLibrary.get(key).getMovieTitle());
        }
        return moviesWatched;
    }
     
    public ArrayList<String> getMoviesLiked(){
        moviesLiked.clear();
        for (String key : moviesLikedLibrary.keySet()){
            moviesLiked.add(moviesLikedLibrary.get(key).getMovieTitle());
        }
        return moviesLiked;
    }
    
    public static HashMap<String, Integer> getItemCounts(ArrayList<String> lst){
        HashMap<String, Integer> Freqs = new HashMap<>();
        for (String i : lst) {
            if (Freqs.get(i)  != null) {
                int count = Freqs.get(i);
                Freqs.put(i, count + 1);
            }
            else{
                Freqs.put(i, 1);
            }
            
        }
        return Freqs;
    }
    
    public static String[] getMax(HashMap<String, Integer> freqHashMap){
        String [] maxArray = new String [2];
        String maxFieldType = null;
        int maxFieldValue = 0;
        //turn this into a static please
        for (String i : freqHashMap.keySet()){
            if (freqHashMap.get(i) > maxFieldValue){
                maxFieldValue = freqHashMap.get(i);
                maxFieldType = i;
            }
        }
        maxArray[0] = maxFieldType; 
        maxArray[1] = Integer.toString(maxFieldValue);
        return maxArray;    
    }
    
    public HashMap<String, Integer> getWatchedCounts(String fieldType) {
        HashMap<String, Integer> watchedFreqs = new HashMap<>();
        ArrayList<String> relevantList = new ArrayList<String>();
        
        if (fieldType.equals("Actor")){
            for (String key : moviesWatchedLibrary.keySet()){
                relevantList.add(moviesWatchedLibrary.get(key).getActorOne());
                relevantList.add(moviesWatchedLibrary.get(key).getActorTwo());
                relevantList.add(moviesWatchedLibrary.get(key).getActorThree());
            }
        }
        
        else if (fieldType.equals("Genre")){
            for (String key : moviesWatchedLibrary.keySet()){
                relevantList.add(moviesWatchedLibrary.get(key).getMovieGenre1());
                relevantList.add(moviesWatchedLibrary.get(key).getMovieGenre2());
                relevantList.add(moviesWatchedLibrary.get(key).getMovieGenre3());
                
            }
        }
            
        else if (fieldType.equals("Director")){
            for (String key : moviesWatchedLibrary.keySet()){
                relevantList.add(moviesWatchedLibrary.get(key).getMovieDirector());
            }
        }
        
        else if (fieldType.equals("Year")){
            for (String key : moviesWatchedLibrary.keySet()){
                relevantList.add(Integer.toString(moviesWatchedLibrary.get(key).getMovieYear()));
            }
        }
        
        watchedFreqs = getItemCounts(relevantList);
        return watchedFreqs;
    }
    
    public String [] getFavorite(String fieldType) {
        HashMap<String, Integer> Freqs = new HashMap<String, Integer>();
        Freqs = this.getWatchedCounts(fieldType);
        return getMax(Freqs);
        //also return maxFieldValue;
    }
    
    public String getMostFrequentDirector() {
        return this.getFavorite("Director")[0];
    }
    
    public String getMostFrequentActor() {
        return this.getFavorite("Actor")[0];
    }
    
    public String getMostFrequentGenre() {
        return this.getFavorite("Genre")[0];
    }
    
    public String getMostFrequentYear() {
        return this.getFavorite("Year")[0];
    }
      
    public String getMostFrequentDirectorValue() {
        return this.getFavorite("Director")[1];
    }
    
    public String getMostFrequentActorValue() {
        return this.getFavorite("Actor")[1];
    }
    
    public String getMostFrequentGenreValue() {
        return this.getFavorite("Genre")[1];
    }
    
    public String getMostFrequentYearValue() {
        return this.getFavorite("Year")[1];
    }
    
    //search methods
    public ArrayList<String> searchMovies(String field, String searchScope){
        ArrayList<String> searchResults = new ArrayList<String>();
        HashMap <String, MovieInfo> libraryToUse = new HashMap<>();
        
        if (searchScope.equals("All")){
            libraryToUse = this.myMovieShop;
        }
        else if (searchScope.equals("Watched")){
            libraryToUse = this.moviesWatchedLibrary;
        }
        
        else if (searchScope.equals("Liked")){
            libraryToUse = this.moviesLikedLibrary;
        }
                          
        for(String key : libraryToUse.keySet())
            {
                        
             if (field.equals(libraryToUse.get(key).getMovieDirector()) || field.equals(libraryToUse.get(key).getMovieGenre1())
                          || field.equals(libraryToUse.get(key).getActorOne()) || field.equals(libraryToUse.get(key).getMovieGenre2()) 
                          || field.equals(libraryToUse.get(key).getMovieGenre3()) || field.equals(libraryToUse.get(key).getActorTwo()) 
                          || field.equals(libraryToUse.get(key).getActorThree()) || field.equals(Integer.toString(libraryToUse.get(key).getMovieYear())))
                {
                 searchResults.add(libraryToUse.get(key).getMovieTitle());
                }
            }  
        //System.out.println("Sorry. No Results Found")
        return searchResults;
    }
    
    public ArrayList<String> searchMovies(String field, String fieldTwo, String fieldType, String fieldTypeTwo, String SearchScope){
        ArrayList<String> searchResults = new ArrayList<String>();
        HashMap <String, MovieInfo> libraryToUse = new HashMap<>();
        
        if (SearchScope.equals("All")){
            libraryToUse = this.myMovieShop;
        }
        else if (SearchScope.equals("Watched")){
            libraryToUse = this.moviesWatchedLibrary;
        }
        
        else if (SearchScope.equals("Liked")){
            libraryToUse = this.moviesLikedLibrary;
        }
        
        if (fieldType.equals("Actor")){
            if (fieldTypeTwo.equals("Director")){
                for(String key : myMovieShop.keySet())
                {
                    if (field.equals(myMovieShop.get(key).getActorOne()) && fieldTwo.equals(myMovieShop.get(key).getMovieDirector())){
                        searchResults.add(myMovieShop.get(key).getMovieTitle());
                    }
                }
            }
            if (fieldTypeTwo.equals("Genre")){
                for(String key : myMovieShop.keySet())
                {
                    if (field.equals(myMovieShop.get(key).getActorOne()) && fieldTwo.equals(myMovieShop.get(key).getMovieGenre1())){
                        searchResults.add(myMovieShop.get(key).getMovieTitle());
                    }
                }
            }
        }
        else if (fieldType.equals("Director")){
            if (fieldTypeTwo.equals("Actor")){
                for(String key : myMovieShop.keySet())
                {
                    if (field.equals(myMovieShop.get(key).getMovieDirector()) && fieldTwo.equals(myMovieShop.get(key).getActorOne())){
                        searchResults.add(myMovieShop.get(key).getMovieTitle());
                    }
                }
            }
            if (fieldTypeTwo.equals("Genre")){
                for(String key : myMovieShop.keySet())
                {
                    if (field.equals(myMovieShop.get(key).getMovieDirector()) && fieldTwo.equals(myMovieShop.get(key).getMovieGenre1())){
                        searchResults.add(myMovieShop.get(key).getMovieTitle());
                    }
                }
            }
        }
        else if (fieldType.equals("Genre")){
            if (fieldTypeTwo.equals("Director")){
                for(String key : myMovieShop.keySet())
                {
                    if (field.equals(myMovieShop.get(key).getMovieGenre1()) && fieldTwo.equals(myMovieShop.get(key).getMovieDirector())){
                        searchResults.add(myMovieShop.get(key).getMovieTitle());
                    }
                }
            }
            if (fieldTypeTwo.equals("Actor")){
                for(String key : myMovieShop.keySet())
                {
                    if (field.equals(myMovieShop.get(key).getMovieGenre1()) && fieldTwo.equals(myMovieShop.get(key).getActorOne())){
                        searchResults.add(myMovieShop.get(key).getMovieTitle());
                    }
                }
            }
        }
        else{
            System.out.println("Make sure search query is valid. You can only choose Director, Actor or Genre");
        }
        return searchResults;
    }
    
    //sorting and filtering methods
    public ArrayList <String> filterMoviesByRating(HashMap<String, MovieInfo> library, double ratingCutoff, int n){
        int i = 0;
        ArrayList <String> filteredMovieRatings = new ArrayList<String>();
        for (String key : library.keySet()){
            if (library.get(key).getiMDBRating() >= ratingCutoff && i <= n){
                filteredMovieRatings.add(library.get(key).getMovieTitle());
                i = i + 1;
            } 
        }
        return filteredMovieRatings;
    }
    
    public static String[][] findTopMovies(HashMap<String, MovieInfo> library, int n){
        
        String [][] topRatedMovies = new String[n][n];
        if (n > library.size())
            System.out.println("You don't have enough movies to return that many. Please choose a smaller limit");
        else{
            for(int i = 0; i < n; i++){
                String highestRatedMovie = null;
                double highestRating = 0;
                for(String key : library.keySet()){
                    if (library.get(key).getiMDBRating() > highestRating){
                        highestRating = library.get(key).getiMDBRating();
                        highestRatedMovie = key;
                    }
                topRatedMovies[i][0] = highestRatedMovie;
                topRatedMovies[i][1] = Double.toString(library.get(highestRatedMovie).getiMDBRating());
                }
                //remove the highest rated movie from the movie list and iterate by finding the next highest rated movie
                library.remove(highestRatedMovie);
            }
        }
        return topRatedMovies;
    }
    
    public String[][] getTopMovies(String returnScope, int movieLimit){
        String [] [] topMovies = new String[movieLimit][movieLimit];
        if (returnScope.equals("Watched")){
            topMovies = this.findTopMovies(moviesWatchedLibrary, movieLimit);
        }
        
        else if (returnScope.equals("Liked")){
            topMovies = this.findTopMovies(moviesLikedLibrary, movieLimit);
        }
        
        else if (returnScope.equals("All")){
            topMovies = this.findTopMovies(myMovieShop, movieLimit);
        } 
        
        return topMovies;
    }
    
    public static double[] getAverages(HashMap<String, MovieInfo> library){
        double aggRatings = 0; 
        double aggYears = 0;
        double numberOfKeys = 0;
        for (String key : library.keySet()){
            aggRatings  = aggRatings + library.get(key).getiMDBRating();
            aggYears  = aggYears + ((double) library.get(key).getMovieYear()*1.00);
            numberOfKeys = numberOfKeys + 1;    
        }
        double[] averageArray = new double[2];
        averageArray[0] = aggRatings*1.000 / numberOfKeys;
        averageArray[1] =  aggYears *1.000 / numberOfKeys ;
        return averageArray;
    }
    
    //recommender methods
    public String [] getNrandomMovies(int n) {
        String [] nRandomMovieList = new String [n];
        Collections.shuffle(completeMovieList);
        Collections.shuffle(completeMovieList);
        Collections.shuffle(completeMovieList);
        
        for (int i = 0; i < n; i++){
            //nRandomMovieList[i] = completeMovieList.get(i);
            nRandomMovieList[i] = completeMovieList.get(i);
        }
        return nRandomMovieList;
    }
    
    public String [] getRecommendedMovies(int n){
        
        double favoriteDirectorPercW  =  Double.parseDouble(this.getMostFrequentDirectorValue())/moviesWatchedLibrary.size();
        double favoriteGenrePercW =  Double.parseDouble(this.getMostFrequentGenreValue()) / moviesWatchedLibrary.size();
        double favroiteActorPercW =  Double.parseDouble(this.getMostFrequentActorValue()) / moviesWatchedLibrary.size();
        //favoriteDirectorL = this.getMostFrequentDirector();
        //favoriteGenreL = this.getMostFrequentGenre();
        //favroiteActorL = this.getMostFrequentActor();
       
        double randomProb;
        double ratingCutoff = getAverages(moviesWatchedLibrary)[0] - 0.5;
        ArrayList <String> recommendedMovieArrayList = new ArrayList <String>();
        ArrayList <String> results = new ArrayList <String>();
                
        HashMap<String, Integer> Freqs = new HashMap<>();
        String maxMovieRecommended;
        String [] movieRecommendations_N = new String [n];
        for (int i =0; i < 100; i++){
            randomProb = Math.random();

            if (randomProb <= favoriteDirectorPercW*3){
                recommendedMovieArrayList.addAll(searchMovies(this.getMostFrequentDirector(), "All"));
            }
            if (randomProb/2 <= favoriteGenrePercW){
                results = searchMovies(this.getMostFrequentGenre(), "All");
                Collections.shuffle(results);
                for (int k=0;k<10;k++){
                    if (k<results.size())
                        recommendedMovieArrayList.add(results.get(k));
                }
            }
            if (randomProb <= favroiteActorPercW*3){
                results = searchMovies(this.getMostFrequentActor(), "All");
                Collections.shuffle(results);
                for (int k=0;k<10;k++){
                    if (k<results.size())
                        recommendedMovieArrayList.add(results.get(k));
                }
            }
            if (randomProb <= 0.5){
                results = filterMoviesByRating(this.myMovieShop, ratingCutoff, 25);
                Collections.shuffle(results);
                for (int k=0;k<10;k++){
                    if (k<results.size())
                        recommendedMovieArrayList.add(results.get(k));
                }
            }
            /*if (randomProb*1.0 <= directorPercentageL)
                directorMovies = searchMovies(favoriteDirectorL)
            if (randomProb/2 <= GenrePercentageL)
                genreMovies = searchMovies(favoriteGenreL)
            if (randomProb <= ActorPercentageL)
                actorMovies = searchMovies(favroiteActorL)*/
            //if (randomProb <= 0.7)
                //recommendedMovieArrayList.addAll(searchMovies(averageYearsW, "Watched"));
       
        }
        
        Freqs = this.getItemCounts(recommendedMovieArrayList);
        for (int j=0; j< n; j++){
            maxMovieRecommended = getMax(Freqs)[0];
            movieRecommendations_N[j] = maxMovieRecommended;
            Freqs.remove(maxMovieRecommended); 
        }
        System.out.println(ratingCutoff);
        return movieRecommendations_N;
    }
   
    /*public boolean equals(Object user2){
        boolean isEqual = false;
        if ((user2 != null) && (user2 instanceof User)){
            User other = (User) user2;
            isEqual = ((this.userName == other.userName) && (this.userAge == other.userAge));
        }
        return isEqual;
    }*/
    
    
    public void saveMoviesWatched(String filepathname) throws IOException{
        FileWriter movieFileWriter = new FileWriter(filepathname);
        for(String movieInfo: moviesLikedLibrary.keySet()){
                movieFileWriter.write(movieInfo);
            }
        movieFileWriter.close();
    }
    
    @Override
    public String toString() {
        return this.userName + "," + this.userAge;
    }
}
///end of class
