package applicatif;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import bean.AdresseType;
import bean.CategorieType;
import bean.CompetenceDetailType;
import bean.CompetenceType;
import bean.Cv;
import bean.ExperienceType;
import bean.FonctionType;
import bean.IdentiteType;
import bean.ObjectFactory;

public class Appli {


    private static ObjectFactory objectFactory = new ObjectFactory();
    private final static String CHEMIN_FICHIER_GENERE = "E:\\wks\\testXSLT\\ressources\\CV_TEST.xml";
    private final static String CHEMIN_SCHEMA = "E:\\wks\\testXSLT\\ressources\\schemaCV.xsd";

    public static void main(String[] args) {
        final Cv cvAC = objectFactory.createCv();
        final IdentiteType identiteTypeAC = objectFactory.createIdentiteType();
        final AdresseType adresseTypeAC = objectFactory.createAdresseType();
        final FonctionType fonctionTypeAC = objectFactory.createFonctionType();
        final CompetenceType competenceTypeTechniqueAC = objectFactory.createCompetenceType();
        final CompetenceDetailType competenceDetailTypeTechniqueDeveloppementAC = objectFactory.createCompetenceDetailType();
        final CompetenceDetailType competenceDetailTypeTechniqueServeurAppliAC = objectFactory.createCompetenceDetailType();
        final CompetenceType competenceTypeFonctionnelleAC = objectFactory.createCompetenceType();
        final CompetenceDetailType competenceDetailTypeFonctionnelleAC = objectFactory.createCompetenceDetailType();
        final ExperienceType experienceTypeAC = objectFactory.createExperienceType();

        initAdresseAC(adresseTypeAC);

        initIdentiteAC(identiteTypeAC, adresseTypeAC);

        initFonctionAC(fonctionTypeAC);

        initCompetenceDetailTechniqueDeveloppementAC(competenceDetailTypeTechniqueDeveloppementAC);
        initCompetenceDetailTechniqueServeurAppliAC(competenceDetailTypeTechniqueServeurAppliAC);

        initCompetenceDetailFonctionnelleAC(competenceDetailTypeFonctionnelleAC);

        competenceTypeTechniqueAC.getCompetence().add(competenceDetailTypeTechniqueDeveloppementAC);
        competenceTypeTechniqueAC.getCompetence().add(competenceDetailTypeTechniqueServeurAppliAC);

        competenceTypeFonctionnelleAC.getCompetence().add(competenceDetailTypeFonctionnelleAC);

        initExperienceAC(experienceTypeAC);

        cvAC.setIdentite(identiteTypeAC);
        cvAC.setFonction(fonctionTypeAC);
        cvAC.setCompetenceTechnique(competenceTypeTechniqueAC);
        cvAC.setCompetenceFonctionnelle(competenceTypeFonctionnelleAC);
        cvAC.getExperience().add(experienceTypeAC);

        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Cv.class);
            final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            final FileWriter writer = new FileWriter(CHEMIN_FICHIER_GENERE);
            writer.write("<?xml-stylesheet type=\"text/xsl\" href=\"CV.xsl\">");
            writer.write("\n");
            
            final File fileSchema = new File(CHEMIN_SCHEMA);
            final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            final Schema schema = schemaFactory.newSchema(fileSchema); 
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setSchema(schema);
            jaxbMarshaller.marshal(cvAC, writer);
        } catch (JAXBException e) {
            System.out.println("erreur lors de la génération du fichier XML.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("erreur sur ke flux de fichier.");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("erreur sur le schema.");
            e.printStackTrace();
        }
    }

    private static void initExperienceAC(final ExperienceType experienceTypeACASP){
        experienceTypeACASP.setClient("AC");
        experienceTypeACASP.setDateDebut(Calendar.getInstance().getTime());
        experienceTypeACASP.setDateFin(Calendar.getInstance().getTime());
        experienceTypeACASP.setDescription("fvbksdhvkjkvkjdbvjksdbvjksbdjkvbdskbvhuigiskjsbvjsbdvsbdivbisdbvsbdvbd");
        experienceTypeACASP.setLocalisation("Paris (75)");
        final List<String> listePileLogicielle = new ArrayList<String>();
        listePileLogicielle.add("java JDK 1.7");
        listePileLogicielle.add("SVN");
        listePileLogicielle.add("MAVEN");
        listePileLogicielle.add("SQLDEVELOER");
        listePileLogicielle.add("ORACLE 10g");
        listePileLogicielle.add("JQuery");
        experienceTypeACASP.getPileLogiciel().addAll(listePileLogicielle);
    }

    private static void initAdresseAC (final AdresseType adresseType){
        adresseType.setCodePostal("12345");
        adresseType.setNumero(null);
        adresseType.setRue("rue de larue");
        adresseType.setVille("Paris");
    }

    private static void initIdentiteAC (final IdentiteType identiteType, final AdresseType adresseType){
        identiteType.setAdresse(adresseType);
        identiteType.setNom("Dorian");
        identiteType.setPrenom("Arno");
    }

    private static void initFonctionAC (final FonctionType fonctionType){
        fonctionType.setAnneeExperience(2);
        fonctionType.setTitre("Analyste développeur Java Jee");
    }

    private static void initCompetenceDetailTechniqueDeveloppementAC (final CompetenceDetailType competenceDetailType){
        competenceDetailType.setTitreCompetence("Développement");
        competenceDetailType.getCategorie().add(initCategorieLangageAC());
        competenceDetailType.getCategorie().add(initCategorieLibrairieAC());
    }

    private static CategorieType initCategorieLangageAC (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("langage");
        categorieType.getLibelleCategorie().add("java");
        categorieType.getLibelleCategorie().add("JavaScript");
        categorieType.getLibelleCategorie().add("ANT");
        categorieType.getLibelleCategorie().add("JQuery");
        categorieType.getLibelleCategorie().add("PHP");
        categorieType.getLibelleCategorie().add("PLSQL");
        return categorieType;
    }

    private static CategorieType initCategorieLibrairieAC (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("librairie");
        categorieType.getLibelleCategorie().add("JMEter");
        categorieType.getLibelleCategorie().add("Junit");
        return categorieType;
    }

    private static void initCompetenceDetailTechniqueServeurAppliAC (final CompetenceDetailType competenceDetailType){
        competenceDetailType.setTitreCompetence("Serveur application");
        competenceDetailType.getCategorie().add(initCategorieServeurAppliAC());
    }


    private static CategorieType initCategorieServeurAppliAC (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("");
        categorieType.getLibelleCategorie().add("glassfish");
        categorieType.getLibelleCategorie().add("TOMCAT");
        return categorieType;
    }


    private static void initCompetenceDetailFonctionnelleAC (final CompetenceDetailType competenceDetailType){
        competenceDetailType.setTitreCompetence("");
        competenceDetailType.getCategorie().add(initCategorieFonctionnelleGestionProjetAC());
        competenceDetailType.getCategorie().add(initCategorieFonctionnelleAnalyseAC());
    }

    private static CategorieType initCategorieFonctionnelleGestionProjetAC (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("");
        categorieType.getLibelleCategorie().add("Gestion et suivi de projet");
        categorieType.getLibelleCategorie().add("méthode AGILE");
        return categorieType;
    }

    private static CategorieType initCategorieFonctionnelleAnalyseAC (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("");
        categorieType.getLibelleCategorie().add("Analyse");
        categorieType.getLibelleCategorie().add("conception");
        return categorieType;
    }
}
