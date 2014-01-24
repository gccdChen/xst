package cn.scau.zzzd.xst.entity;

/**
 * 销售项
 * @author gccd
 *
 */
public class Sellitem {
    private long id;
    private long bookid;
    private Book goods;
    /**
     * 成色 保留
     */
    private int fresh = 0;
    /**
     * 卖家留言 保留
     */
    private String note;
    /**
     * 照片路径 作为保留
     */
    private String imagePaths;
    
    private float price = 0.0f;
    public Sellitem() {
		// TODO Auto-generated constructor stub
	}
    
    public Sellitem(Book goods) {
		super();
		this.goods = goods;
		this.bookid = goods.getCid();
	}


	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getBookid() {
        return bookid;
    }
    public void setBookid(long bookid) {
        this.bookid = bookid;
    }
    public Book getGoods() {
        return goods;
    }
    public void setGoods(Book goods) {
        this.goods = goods;
    }
    public int getFresh() {
        return fresh;
    }
    public void setFresh(int fresh) {
        this.fresh = fresh;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getImagePaths() {
        return imagePaths;
    }
    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
    
    
}