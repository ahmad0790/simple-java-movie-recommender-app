public class MovieInfo{

    private double movieDuration;
    private int movieYear;
    private double iMDBRating;
    private String actorOne;
    private String actorTwo;
    private String actorThree;
    private String movieDirector;
    private String movieTitle;
    private String movieGenre1;
    private String movieGenre2;    
    private String movieGenre3;
    private String contentRating;
    
    public MovieInfo(String mDir, double mDur, String ac2, String ac1, String mTitl, String ac3
                     ,String cr, int mYear, double iScore, String mGen1, String mGen2, String mGen3){
    movieDirector = mDir;
    movieDuration = mDur;
    movieYear = mYear;
    iMDBRating = iScore;
    actorOne = ac1;
    actorTwo = ac2;
    actorThree = ac3;
    movieTitle = mTitl;
    movieGenre1 = mGen1;
    movieGenre2 = mGen2;
    movieGenre3 = mGen3;
    contentRating = cr;
   }
   //getter methods for each instance variable
    public double getMovieDuration() {
        return movieDuration;
    }
    
    public String getMovieGenre1() {
        return movieGenre1;
    }
    
    public String getMovieGenre2() {
        return movieGenre2;
    }
    
    public String getMovieGenre3() {
        return movieGenre3;
    }
    
    public String getContentRating() {
        return contentRating;
    }
    
    public int getMovieYear() {
        return movieYear;
    }

    public double getiMDBRating() {
        return iMDBRating;
    }
    
    public String getActorOne() {
        return actorOne;
    }
    
    public String getMovieDirector() {
        return movieDirector;
    }
    
    public String getActorTwo() {
        return actorTwo;
    }
    
    public String getActorThree() {
        return actorThree;
    }
    
    public String getMovieTitle() {
        return movieTitle;
    }
    
    //setter methods for each instance variable
    public void setMovieDuration(double movieDuration) {
        this.movieDuration = movieDuration;
    }
    
    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public void setiMDBRating(double iMDBRating) {
        this.iMDBRating = iMDBRating;
    }
    
    public void setActorOne(String actorOne) {
        this.actorOne = actorOne;
    }

    public void setActorTwo(String actorTwo) {
        this.actorTwo = actorTwo;
    }

    public void setActorThree(String actorThree) {
        this.actorThree = actorThree;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    
    public void setMovieGenre1(String movieGenre1) {
        this.movieGenre1 = movieGenre1;
    }
    
    public void setMovieGenre2(String movieGenre2) {
        this.movieGenre2 = movieGenre2;
    }
    
    public void setMovieGenre3(String movieGenre3) {
        this.movieGenre3 = movieGenre3;
    }
    
    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }
    
    public String toString() {
        return "Movie Director "+ this.movieDirector + " Movie Genre: " + this.movieGenre1 + "," + this.movieGenre2 + "," +this.movieGenre3 + " Movie Duration: " + this.movieDuration + " Movie Year: " 
        + this.movieYear + " IMDB Rating: " + this.iMDBRating + " Actors: " + this.actorOne + "," + this.actorTwo + "," + this.actorThree + "Content Rating " + this.contentRating;
    }
}
