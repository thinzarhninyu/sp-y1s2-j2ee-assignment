// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03

package CA2;

public class Manga extends Comic {

    private String language;

    public Manga(String isbn, String title, int pages, double cost, String language) {
          super(isbn, title, pages, cost);
        this.language = language;
    }
    
    public String getLanguage() {
        if ("EN".equals(language)) {
            return "This is a Manga translated to English";
        } else {
            return "This is a Manga in Japanese";
        }
    }
    
    public void setLanguage(String language){
        this.language = language;
    }
}
