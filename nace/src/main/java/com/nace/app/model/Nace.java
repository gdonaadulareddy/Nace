/**
 * Copy Right 2022.
 */
package com.nace.app.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Gowtham.
 *
 */
@Entity
@Table(name = "nace")
public class Nace {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "`Order`")
	private Long order;
	
	@Column(name = "Level")
	private Long level;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "Parent")
	private String parent;
	
	@Column(name = "Description")
	@Lob
	private String description;
	
	@Column(name = "`This item includes`")
	@Lob
	private String itemsIncluded;
	
	@Column(name = "`This item also includes`")
	@Lob
	private String itemsAlsoIncluded;
	
	@Column(name = "Rulings")
	@Lob
	private String rulings;
	
	@Column(name = "`This item excludes`")
	@Lob
	private String itemsExcluded;
	
	@Column(name = "`Reference to ISIC Rev. 4`")
	private String referenceToISICRev4;

	/**
	 * @param id
	 * @param order
	 * @param level
	 * @param code
	 * @param parent
	 * @param description
	 * @param itemsIncluded
	 * @param itemsAlsoIncluded
	 * @param rulings
	 * @param itemsExcluded
	 * @param referenceToISICRev4
	 */
	public Nace(Long id, Long order, Long level, String code, String parent, String description, String itemsIncluded,
			String itemsAlsoIncluded, String rulings, String itemsExcluded, String referenceToISICRev4) {
		super();
		this.id = id;
		this.order = order;
		this.level = level;
		this.code = code;
		this.parent = parent;
		this.description = description;
		this.itemsIncluded = itemsIncluded;
		this.itemsAlsoIncluded = itemsAlsoIncluded;
		this.rulings = rulings;
		this.itemsExcluded = itemsExcluded;
		this.referenceToISICRev4 = referenceToISICRev4;
	}

	/**
	 * @param order
	 * @param level
	 * @param code
	 * @param parent
	 * @param description
	 * @param itemsIncluded
	 * @param itemsAlsoIncluded
	 * @param rulings
	 * @param itemsExcluded
	 * @param referenceToISICRev4
	 */
	public Nace(Long order, Long level, String code, String parent, String description, String itemsIncluded,
			String itemsAlsoIncluded, String rulings, String itemsExcluded, String referenceToISICRev4) {
		super();
		this.order = order;
		this.level = level;
		this.code = code;
		this.parent = parent;
		this.description = description;
		this.itemsIncluded = itemsIncluded;
		this.itemsAlsoIncluded = itemsAlsoIncluded;
		this.rulings = rulings;
		this.itemsExcluded = itemsExcluded;
		this.referenceToISICRev4 = referenceToISICRev4;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemsIncluded() {
		return itemsIncluded;
	}

	public void setItemsIncluded(String itemsIncluded) {
		this.itemsIncluded = itemsIncluded;
	}

	public String getItemsAlsoIncluded() {
		return itemsAlsoIncluded;
	}

	public void setItemsAlsoIncluded(String itemsAlsoIncluded) {
		this.itemsAlsoIncluded = itemsAlsoIncluded;
	}

	public String getRulings() {
		return rulings;
	}

	public void setRulings(String rulings) {
		this.rulings = rulings;
	}

	public String getItemsExcluded() {
		return itemsExcluded;
	}

	public void setItemsExcluded(String itemsExcluded) {
		this.itemsExcluded = itemsExcluded;
	}

	public String getReferenceToISICRev4() {
		return referenceToISICRev4;
	}

	public void setReferenceToISICRev4(String referenceToISICRev4) {
		this.referenceToISICRev4 = referenceToISICRev4;
	}

	/**
	 * 
	 */
	public Nace() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description, id, itemsAlsoIncluded, itemsExcluded, itemsIncluded, level, order,
				parent, referenceToISICRev4, rulings);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nace other = (Nace) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(itemsAlsoIncluded, other.itemsAlsoIncluded)
				&& Objects.equals(itemsExcluded, other.itemsExcluded)
				&& Objects.equals(itemsIncluded, other.itemsIncluded) && Objects.equals(level, other.level)
				&& Objects.equals(order, other.order) && Objects.equals(parent, other.parent)
				&& Objects.equals(referenceToISICRev4, other.referenceToISICRev4)
				&& Objects.equals(rulings, other.rulings);
	}

	@Override
	public String toString() {
		return "Nace [id=" + id + ", order=" + order + ", level=" + level + ", code=" + code + ", parent=" + parent
				+ ", description=" + description + ", itemsIncluded=" + itemsIncluded + ", itemsAlsoIncluded="
				+ itemsAlsoIncluded + ", rulings=" + rulings + ", itemsExcluded=" + itemsExcluded
				+ ", referenceToISICRev4=" + referenceToISICRev4 + "]";
	}
	
	
	
	
}
