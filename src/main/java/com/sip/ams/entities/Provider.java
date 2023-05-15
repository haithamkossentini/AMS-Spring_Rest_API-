package com.sip.ams.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;
    @JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "provider")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticle(List<Article> articles) {
        this.articles = articles;
    }
    public Provider() {}

    public Provider(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

	@Override
	public String toString() {
		return "Provider [name=" + name + ", address=" + address + ", email=" + email + ", articles=" + articles + "]";
	}

	/*@Override
	public String toString() {
		return "Provider [name=" + name + ", address=" + address + ", email=" + email + "]";
	}*/

	
    
}
