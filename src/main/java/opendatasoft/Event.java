package opendatasoft;

import java.util.Date;

public class Event {
	private String id;
	private String freetext;
	private String city;
	private String title;
	private String price;
	private String datestart;
	private String department;
	private String dateend;
	private String description;
	private String link;
	private String address;
	private String region;
	private String image;
	private String timeInfo;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Event\n");
		sb.append("id: "+id+"\n");
		sb.append("freetext: "+freetext+"\n");
		sb.append("city: "+city+"\n");
		sb.append("title: "+title+"\n");
		sb.append("price"+price+"\n");
		sb.append("datestart: "+datestart+"\n");
		sb.append("department: "+department+"\n");
		sb.append("dateend: "+dateend+"\n");
		sb.append("description: "+description+"\n");
		sb.append("link: "+link+"\n");
		sb.append("address: "+address+"\n");
		sb.append("region: "+region+"\n");
		sb.append("image: "+image+"\n");
		sb.append("timeInfo: "+timeInfo+"\n");
		return sb.toString();
		
	}
	
	
	public Event(String id, String freetext, String city, String title, String price, String datestart,
			String department, String dateend, String description, String link, String address, String region,
			String image, String timeInfo) {
		super();
		this.id = id;
		this.freetext = freetext;
		this.city = city;
		this.title = title;
		this.price = price;
		this.datestart = datestart;
		this.department = department;
		this.dateend = dateend;
		this.description = description;
		this.link = link;
		this.address = address;
		this.region = region;
		this.image = image;
		this.timeInfo = timeInfo;
	}
	
	public String getTimeInfo() {
		return timeInfo;
	}


	public void setTimeInfo(String timeInfo) {
		this.timeInfo = timeInfo;
	}


	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFreetext() {
		return freetext;
	}
	public void setFreetext(String freetext) {
		this.freetext = freetext;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDatestart() {
		return datestart;
	}
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDateend() {
		return dateend;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	
	
	
	
	
	
}
