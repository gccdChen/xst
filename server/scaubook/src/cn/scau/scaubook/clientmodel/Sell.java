package cn.scau.scaubook.clientmodel;

public class Sell {
    private long bookid;
    private float price;
    private String note;
    
    public Sell() {
        // TODO Auto-generated constructor stub
    }
    public Sell(long bookid, float price, String note) {
        super();
        this.bookid = bookid;
        this.price = price;
        this.note = note;
    }
    public long getBookid() {
        return bookid;
    }
    public void setBookid(long bookid) {
        this.bookid = bookid;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    
}
