#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: TYPE_PATIENT
#------------------------------------------------------------

CREATE TABLE TYPE_PATIENT(
        id_type  Int  Auto_increment  NOT NULL ,
        intitule Varchar (250) NOT NULL
	,CONSTRAINT TYPE_PATIENT_PK PRIMARY KEY (id_type)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PATIENT
#------------------------------------------------------------

CREATE TABLE PATIENT(
        id_patient         Int  Auto_increment  NOT NULL ,
        nom                Varchar (250) NOT NULL ,
        prenom             Varchar (250) NOT NULL ,
        adresse            Varchar (250) NOT NULL ,
        code_postal        Int NOT NULL ,
        ville              Varchar (250) NOT NULL ,
        telephone          Int NOT NULL ,
        moyen_connaissance Varchar (250) NOT NULL ,
        email              Varchar (250) NOT NULL ,
        id_type            Int NOT NULL
	,CONSTRAINT PATIENT_PK PRIMARY KEY (id_patient)

	,CONSTRAINT PATIENT_TYPE_PATIENT_FK FOREIGN KEY (id_type) REFERENCES TYPE_PATIENT(id_type)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PROFESSION
#------------------------------------------------------------

CREATE TABLE PROFESSION(
        id_profession Int  Auto_increment  NOT NULL ,
        intitule      Varchar (250) NOT NULL ,
        annee         Varchar (250) NOT NULL ,
        id_patient    Int NOT NULL
	,CONSTRAINT PROFESSION_PK PRIMARY KEY (id_profession)

	,CONSTRAINT PROFESSION_PATIENT_FK FOREIGN KEY (id_patient) REFERENCES PATIENT(id_patient)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SEANCE
#------------------------------------------------------------

CREATE TABLE SEANCE(
        id_seance Int  NOT NULL ,
        date      Date NOT NULL ,
        creaneau  Varchar (250) NOT NULL
	,CONSTRAINT SEANCE_PK PRIMARY KEY (id_seance)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TYPE_CONSULTATION
#------------------------------------------------------------

CREATE TABLE TYPE_CONSULTATION(
        id_type_consultation Int  Auto_increment  NOT NULL ,
        intitule             Varchar (250) NOT NULL
	,CONSTRAINT TYPE_CONSULTATION_PK PRIMARY KEY (id_type_consultation)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: consulter
#------------------------------------------------------------

CREATE TABLE consulter(
        id_patient           Int NOT NULL ,
        id_seance            Int NOT NULL ,
        id_type_consultation Int NOT NULL ,
        indicateur           Int NOT NULL ,
        reglement            Varchar (250) NOT NULL ,
        prix                 Int ,
        posture              Varchar (250) ,
        comportement         Text
	,CONSTRAINT consulter_PK PRIMARY KEY (id_patient,id_seance,id_type_consultation)

	,CONSTRAINT consulter_PATIENT_FK FOREIGN KEY (id_patient) REFERENCES PATIENT(id_patient)
	,CONSTRAINT consulter_SEANCE0_FK FOREIGN KEY (id_seance) REFERENCES SEANCE(id_seance)
	,CONSTRAINT consulter_TYPE_CONSULTATION1_FK FOREIGN KEY (id_type_consultation) REFERENCES TYPE_CONSULTATION(id_type_consultation)
)ENGINE=InnoDB;

