

package com.sip.ams.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Label is mandatory")
    @Column(name = "label")
    private String label;

    @Column(name = "price")
    private Float price;

    @NotBlank(message = "Picture is mandatory")
    @Column(name = "picture")
    private String picture;

    /**** Many To One ****/	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Provider provider;
    
    public Article() {};
	/*public Article(@NotBlank(message = "Label is mandatory") String label, @NotBlank(message = "price") Float price,
			@NotBlank(message = "Picture is mandatory") String picture) {
		super();
		this.label = label;
		this.price = price;
		this.picture = picture;
	}*/
    public Article(String label, float price, String picture) {
        this.price = price;
        this.label = label;
        this.picture = picture;
        }
	
    public Article(String label, float price, String picture,Provider provider) {
        this.price = price;
        this.label = label;
        this.picture = picture;
        this.provider = provider;
        }

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	/*@Override
	public String toString() {
		return "Article [label=" + label + ", price=" + price + ", picture=" + picture + "]";
	}*/
	@Override
	public String toString() {
		return "Article [label=" + label + ", price=" + price + ", picture=" + picture + ", provider=" + provider + "]";
	}
    
}
