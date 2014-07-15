package applicatif;

import bean.AdresseType;
import bean.CategorieType;
import bean.CompetenceDetailType;
import bean.CompetenceType;
import bean.Cv;
import bean.ExperienceType;
import bean.FonctionType;
import bean.IdentiteType;
import bean.ObjectFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Appli {

    static {
        final SimpleDateFormat formatDate= new SimpleDateFormat("yyyyMMdd");
        final Calendar dateJour = Calendar.getInstance();
        dateJourFormat = formatDate.format(dateJour.getTime());
    }


    private static ObjectFactory objectFactory = new ObjectFactory();
    private static String dateJourFormat;

    private final static String NOM = "Martin";
    private final static String PRENOM = "Jean";


    private final static String CHEMIN_FICHIER_GENERE = "ressources\\CV_"+NOM+"_"+PRENOM+"_"+dateJourFormat+".xml";
    private final static String CHEMIN_SCHEMA ="ressources\\schemaCV.xsd";

    public static void main(String[] args) {
        final Cv cvUtilisateur = objectFactory.createCv();
        final IdentiteType identiteTypeUtilisateur = objectFactory.createIdentiteType();
        final AdresseType adresseTypeUtilisateur = objectFactory.createAdresseType();
        final FonctionType fonctionTypeUtilisateur = objectFactory.createFonctionType();
        final CompetenceType competenceTypeTechniqueUtilisateur = objectFactory.createCompetenceType();
        final CompetenceDetailType competenceDetailTypeTechniqueDeveloppementUtilisateur = objectFactory.createCompetenceDetailType();
        final CompetenceDetailType competenceDetailTypeTechniqueServeurAppliUtilisateur = objectFactory.createCompetenceDetailType();
        final CompetenceType competenceTypeFonctionnelleUtilisateur = objectFactory.createCompetenceType();
        final CompetenceDetailType competenceDetailTypeFonctionnelleUtilisateur = objectFactory.createCompetenceDetailType();
        final ExperienceType experienceTypeUtilisateurASP = objectFactory.createExperienceType();
        final ExperienceType experienceTypeUtilisateurCarsat = objectFactory.createExperienceType();

        initAdresseUtilisateur(adresseTypeUtilisateur);

        initIdentiteUtilisateur(identiteTypeUtilisateur, adresseTypeUtilisateur);

        initFonctionUtilisateur(fonctionTypeUtilisateur);

        initCompetenceDetailTechniqueDeveloppementUtilisateur(competenceDetailTypeTechniqueDeveloppementUtilisateur);
        initCompetenceDetailTechniqueServeurAppliUtilisateur(competenceDetailTypeTechniqueServeurAppliUtilisateur);

        initCompetenceDetailFonctionnelleUtilisateur(competenceDetailTypeFonctionnelleUtilisateur);

        competenceTypeTechniqueUtilisateur.getCompetence().add(competenceDetailTypeTechniqueDeveloppementUtilisateur);
        competenceTypeTechniqueUtilisateur.getCompetence().add(competenceDetailTypeTechniqueServeurAppliUtilisateur);

        competenceTypeFonctionnelleUtilisateur.getCompetence().add(competenceDetailTypeFonctionnelleUtilisateur);

        initPremiererExperienceCV(experienceTypeUtilisateurASP);
        initDeuxiemeExperienceCV(experienceTypeUtilisateurCarsat);

        cvUtilisateur.setIdentite(identiteTypeUtilisateur);
        cvUtilisateur.setFonction(fonctionTypeUtilisateur);
        cvUtilisateur.setCompetenceTechnique(competenceTypeTechniqueUtilisateur);
        cvUtilisateur.setCompetenceFonctionnelle(competenceTypeFonctionnelleUtilisateur);
        cvUtilisateur.getExperience().add(experienceTypeUtilisateurCarsat);
        cvUtilisateur.getExperience().add(experienceTypeUtilisateurASP);

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
            jaxbMarshaller.marshal(cvUtilisateur, writer);
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

    private static void initPremiererExperienceCV(final ExperienceType experienceType){
        final List<String> listePileLogicielle = new ArrayList<>();
        listePileLogicielle.add("java JDK 1.7");
        listePileLogicielle.add("SVN");
        listePileLogicielle.add("MAVEN");
        listePileLogicielle.add("SQLDEVELOER");
        listePileLogicielle.add("ORACLE 10g");
        listePileLogicielle.add("JQuery");
        
        final List<String> listeDescription = new ArrayList<>();
        listeDescription.add("Description de l'expérienece effectuée au sein du client.");
        listeDescription.add("Précision de la tâche effectuée, rédaction de document, AMOA etc.");
        
        initExperience(experienceType, "Client 1", listeDescription, "Limoges (87)", listePileLogicielle, initDate(2010, 12, 12), initDate(2011, 12, 12));
    }
    
    private static void initDeuxiemeExperienceCV(final ExperienceType experienceType){
        final List<String> listePileLogicielle = new ArrayList<>();
        listePileLogicielle.add("java JDK 1.6");
        listePileLogicielle.add("SVN");
        listePileLogicielle.add("MAVEN");
        listePileLogicielle.add("TOAD");
        listePileLogicielle.add("ORACLE 8g");
        listePileLogicielle.add("WebLogic");
        
        final List<String> listeDescription = new ArrayList<>();
        listeDescription.add("Description de l'expérienece effectuée au sein du client.");
        listeDescription.add("Précision de la tâche effectuée, rédaction de document, AMOA etc.");
        
        initExperience(experienceType, "Client 2", listeDescription, "Limoges (87)", listePileLogicielle, initDate(2011, 12, 12), initDate(2012, 12, 12));
    }
    
    private static void initExperience (final ExperienceType experienceType, final String nomClient, final List<String> listeDescriptionExperience, final String localisation, final List<String> listePileLogicielle, final Date dateDebut, final Date dateFin){
        experienceType.setClient(nomClient);
        experienceType.setLocalisation(localisation);
        experienceType.setDateDebut(dateDebut);
        experienceType.setDateFin(dateFin);

        experienceType.getDescription().addAll(listeDescriptionExperience);
        experienceType.getPileLogiciel().addAll(listePileLogicielle);
    }

    private static void initAdresseUtilisateur(final AdresseType adresseType){
        adresseType.setCodePostal("87000");
        adresseType.setNumero(null);
        adresseType.setRue("rue du canal");
        adresseType.setVille("Limoges");
    }

    private static void initIdentiteUtilisateur(final IdentiteType identiteType, final AdresseType adresseType){
        identiteType.setAdresse(adresseType);
        identiteType.setNom(NOM);
        identiteType.setPrenom(PRENOM);
    }

    private static void initFonctionUtilisateur(final FonctionType fonctionType){
        fonctionType.setAnneeExperience(4);
        fonctionType.setTitre("Analyste développeur Java Jee");
    }

    private static void initCompetenceDetailTechniqueDeveloppementUtilisateur(final CompetenceDetailType competenceDetailType){
        competenceDetailType.setTitreCompetence("Développement");
        competenceDetailType.getCategorie().add(initCategorieLangageUtilisateur());
        competenceDetailType.getCategorie().add(initCategorieLibrairieUtilisateur());
    }

    private static CategorieType initCategorieLangageUtilisateur (){
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

    private static CategorieType initCategorieLibrairieUtilisateur (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("librairie");
        categorieType.getLibelleCategorie().add("JMEter");
        categorieType.getLibelleCategorie().add("Junit");
        return categorieType;
    }

    private static void initCompetenceDetailTechniqueServeurAppliUtilisateur (final CompetenceDetailType competenceDetailType){
        competenceDetailType.setTitreCompetence("Serveur application");
        competenceDetailType.getCategorie().add(initCategorieServeurAppliUtilisateur());
    }


    private static CategorieType initCategorieServeurAppliUtilisateur (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("");
        categorieType.getLibelleCategorie().add("glassfish");
        categorieType.getLibelleCategorie().add("TOMCAT");
        return categorieType;
    }


    private static void initCompetenceDetailFonctionnelleUtilisateur (final CompetenceDetailType competenceDetailType){
        competenceDetailType.setTitreCompetence("");
        competenceDetailType.getCategorie().add(initCategorieFonctionnelleGestionProjetUtilisateur());
        competenceDetailType.getCategorie().add(initCategorieFonctionnelleAnalyseUtilisateur());
    }

    private static CategorieType initCategorieFonctionnelleGestionProjetUtilisateur (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("");
        categorieType.getLibelleCategorie().add("Gestion et suivi de projet");
        categorieType.getLibelleCategorie().add("méthode AGILE");
        return categorieType;
    }

    private static CategorieType initCategorieFonctionnelleAnalyseUtilisateur (){
        CategorieType categorieType = objectFactory.createCategorieType();
        categorieType.setTitreCategorie("");
        categorieType.getLibelleCategorie().add("Analyse");
        categorieType.getLibelleCategorie().add("conception");
        return categorieType;
    }
    
    private static Date initDate(final int annee, final int mois, final int jour){
        final Calendar date = Calendar.getInstance();
        date.clear();
        date.set(annee, mois-1, jour);
        return date.getTime();
    }
}
