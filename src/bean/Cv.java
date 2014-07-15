//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.11 at 10:57:16 PM CEST 
//


package bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identite" type="{http://www.example.org/schemaCV}identiteType"/>
 *         &lt;element name="fonction" type="{http://www.example.org/schemaCV}fonctionType"/>
 *         &lt;element name="competenceTechnique" type="{http://www.example.org/schemaCV}competenceType"/>
 *         &lt;element name="competenceFonctionnelle" type="{http://www.example.org/schemaCV}competenceType"/>
 *         &lt;element name="experience" type="{http://www.example.org/schemaCV}experienceType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "identite",
    "fonction",
    "competenceTechnique",
    "competenceFonctionnelle",
    "experience"
})
@XmlRootElement(name = "cv")
public class Cv {

    @XmlElement(required = true)
    protected IdentiteType identite;
    @XmlElement(required = true)
    protected FonctionType fonction;
    @XmlElement(required = true)
    protected CompetenceType competenceTechnique;
    @XmlElement(required = true)
    protected CompetenceType competenceFonctionnelle;
    @XmlElement(required = true)
    protected List<ExperienceType> experience;

    /**
     * Gets the value of the identite property.
     * 
     * @return
     *     possible object is
     *     {@link bean.IdentiteType }
     *     
     */
    public IdentiteType getIdentite() {
        return identite;
    }

    /**
     * Sets the value of the identite property.
     * 
     * @param value
     *     allowed object is
     *     {@link bean.IdentiteType }
     *     
     */
    public void setIdentite(IdentiteType value) {
        this.identite = value;
    }

    /**
     * Gets the value of the fonction property.
     * 
     * @return
     *     possible object is
     *     {@link bean.FonctionType }
     *     
     */
    public FonctionType getFonction() {
        return fonction;
    }

    /**
     * Sets the value of the fonction property.
     * 
     * @param value
     *     allowed object is
     *     {@link bean.FonctionType }
     *     
     */
    public void setFonction(FonctionType value) {
        this.fonction = value;
    }

    /**
     * Gets the value of the competenceTechnique property.
     * 
     * @return
     *     possible object is
     *     {@link bean.CompetenceType }
     *     
     */
    public CompetenceType getCompetenceTechnique() {
        return competenceTechnique;
    }

    /**
     * Sets the value of the competenceTechnique property.
     * 
     * @param value
     *     allowed object is
     *     {@link bean.CompetenceType }
     *     
     */
    public void setCompetenceTechnique(CompetenceType value) {
        this.competenceTechnique = value;
    }

    /**
     * Gets the value of the competenceFonctionnelle property.
     * 
     * @return
     *     possible object is
     *     {@link bean.CompetenceType }
     *     
     */
    public CompetenceType getCompetenceFonctionnelle() {
        return competenceFonctionnelle;
    }

    /**
     * Sets the value of the competenceFonctionnelle property.
     * 
     * @param value
     *     allowed object is
     *     {@link bean.CompetenceType }
     *     
     */
    public void setCompetenceFonctionnelle(CompetenceType value) {
        this.competenceFonctionnelle = value;
    }

    /**
     * Gets the value of the experience property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the experience property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExperience().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link bean.ExperienceType }
     * 
     * 
     */
    public List<ExperienceType> getExperience() {
        if (experience == null) {
            experience = new ArrayList<ExperienceType>();
        }
        return this.experience;
    }

}
