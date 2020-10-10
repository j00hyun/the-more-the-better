package io.playdata.themorethebetter.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Store extends BaseTimeEntity
{
	@Id @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@Column(name="ST_NO")
	private Long no; // 가게 고유번호 
	
	 
	@Column(name="ST_NAME", length=40, nullable=false)
	private String name; // 가게 이름 
	
	@Column(name="ST_ADDR", length=50, nullable=false)
	private String address; // 가게 주소 
	
	//@Lob
	@Column(name="ST_PICTURE")
	private String picture; // 가게 사진  

	@JsonIgnore
	@OneToMany(mappedBy="store")
	private List<Waiting> waiting = new ArrayList<Waiting>(); // 진행중인 주문 정보

	@Builder
	public Store(String name, String address, String picture, List<Waiting> waiting) {
		super();
		this.name = name;
		this.address = address;
		this.picture = picture;
		this.waiting = waiting;
	}

}	