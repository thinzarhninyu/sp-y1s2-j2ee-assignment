// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03

package CA2;

public class Comic {
    private String isbn;
    private String title;
    private int pages;
    private double cost;
    
    public Comic(String isbn, String title, int pages, double cost){
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.cost = cost;
    }
    
    public String getIsbn(){
        return isbn;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getPages(){
        return pages;
    }
    
    public double getCost(){
        return cost;
    }
    
    public String getLanguage(){
        return "This is a Comic in English";
    }
    
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setPages(int pages){
        this.pages = pages;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }
    
    public double calculateDayPrice(){
        return (cost/20);
    } 
    
    public double calculateDepositFee(){
        return (cost+(cost*0.1));
    }
}
