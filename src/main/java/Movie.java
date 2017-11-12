import java.util.Comparator;

public class Movie implements Comparator<Movie>, Comparable<Movie> {
    private final String year;
    private final String countryCode;
    private final String title;

    public Movie(String year, String countryCode, String title) {
        this.year = year;
        this.countryCode = countryCode;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}
        if (!(obj instanceof Movie)) {return false;}

        Movie other = (Movie) obj;
        return this.title.equals(other.title) && this.year.equals(other.year) && this.countryCode.equals(other.countryCode);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + this.title.hashCode();
        result = 37*result + this.year.hashCode();
        result = 37*result + this.countryCode.hashCode();

        return result;
    }

    @Override
    public int compareTo(Movie movie) {
        return (this.title).compareToIgnoreCase(movie.title);
    }

    @Override
    public int compare(Movie m1, Movie m2) {
        return (m1.title).compareTo(m2.title);
    }

    @Override
    public String toString() {
        return year + "\t" + countryCode + "\t" + title;
    }
}
