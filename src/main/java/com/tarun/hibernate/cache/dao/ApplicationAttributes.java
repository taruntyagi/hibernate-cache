/**
 * 
 */
package com.tarun.hibernate.cache.dao;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The Class ApplicationAttributes.
 *
 * @author taruntyagi
 */
@Cacheable
@Entity(name="ApplicationAttributes")
@Table(name = "APPLICATIONATTRIBUTES")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region="AppAttributes")
public class ApplicationAttributes implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** The application attribute id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicationAttributeId")
	private int applicationAttributeId;

	/** The attribute description. */
	@Column(name = "attributeDescription")
	private String attributeDescription;

	/** The attribute id. */
	@Column(name = "attributeId")
	private int attributeId;

	/** The attribute name. */
	@Column(name = "attributeName")
	private String attributeName;

	/** The attribute type. */
	@Column(name = "attributeType")
	private String attributeType;

	/**
	 * Gets the application attribute id.
	 *
	 * @return the applicationAttributeId
	 */
	public int getApplicationAttributeId() {
		return applicationAttributeId;
	}

	/**
	 * Sets the application attribute id.
	 *
	 * @param applicationAttributeId
	 *            the applicationAttributeId to set
	 */
	public void setApplicationAttributeId(int applicationAttributeId) {
		this.applicationAttributeId = applicationAttributeId;
	}

	/**
	 * Gets the attribute description.
	 *
	 * @return the attributeDescription
	 */
	public String getAttributeDescription() {
		return attributeDescription;
	}

	/**
	 * Sets the attribute description.
	 *
	 * @param attributeDescription
	 *            the attributeDescription to set
	 */
	public void setAttributeDescription(String attributeDescription) {
		this.attributeDescription = attributeDescription;
	}

	/**
	 * Gets the attribute id.
	 *
	 * @return the attributeId
	 */
	public int getAttributeId() {
		return attributeId;
	}

	/**
	 * Sets the attribute id.
	 *
	 * @param attributeId
	 *            the attributeId to set
	 */
	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	/**
	 * Gets the attribute name.
	 *
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Sets the attribute name.
	 *
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * Gets the attribute type.
	 *
	 * @return the attributeType
	 */
	public String getAttributeType() {
		return attributeType;
	}

	/**
	 * Sets the attribute type.
	 *
	 * @param attributeType
	 *            the attributeType to set
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicationAttributes [applicationAttributeId=" + applicationAttributeId + ", attributeDescription="
				+ attributeDescription + ", attributeId=" + attributeId + ", attributeName=" + attributeName
				+ ", attributeType=" + attributeType + "]";
	}

}
