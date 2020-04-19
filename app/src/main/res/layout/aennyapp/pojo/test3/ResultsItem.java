package design.swira.aennyapp.pojo.test3;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResultsItem{

	@SerializedName("thumbnail")
	private Thumbnail thumbnail;

	@SerializedName("urls")
	private List<UrlsItem> urls;

	@SerializedName("stories")
	private Stories stories;

	@SerializedName("series")
	private Series series;

	@SerializedName("comics")
	private Comics comics;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("modified")
	private String modified;

	@SerializedName("id")
	private int id;

	@SerializedName("resourceURI")
	private String resourceURI;

	@SerializedName("events")
	private Events events;

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setUrls(List<UrlsItem> urls){
		this.urls = urls;
	}

	public List<UrlsItem> getUrls(){
		return urls;
	}

	public void setStories(Stories stories){
		this.stories = stories;
	}

	public Stories getStories(){
		return stories;
	}

	public void setSeries(Series series){
		this.series = series;
	}

	public Series getSeries(){
		return series;
	}

	public void setComics(Comics comics){
		this.comics = comics;
	}

	public Comics getComics(){
		return comics;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setResourceURI(String resourceURI){
		this.resourceURI = resourceURI;
	}

	public String getResourceURI(){
		return resourceURI;
	}

	public void setEvents(Events events){
		this.events = events;
	}

	public Events getEvents(){
		return events;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",urls = '" + urls + '\'' + 
			",stories = '" + stories + '\'' + 
			",series = '" + series + '\'' + 
			",comics = '" + comics + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",modified = '" + modified + '\'' + 
			",id = '" + id + '\'' + 
			",resourceURI = '" + resourceURI + '\'' + 
			",events = '" + events + '\'' + 
			"}";
		}
}