package rs.ebook.domain;

public class Ebook {

	private int id;
	private String title;
	private String author;
	private int numberOfPages;
	private String edition;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public int getNumberOfPages(){
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages){
		this.numberOfPages = numberOfPages;
	}

	public String getEdition(){
		return edition;
	}

	public void setEdition(String edition){
		this.edition = edition;
	}

}
