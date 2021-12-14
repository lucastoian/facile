insert into utente values('Marcolino','Panuccia','asdsad@gmail.com','topogigio','MRCPNC00H23Z129G'); 
insert into utente values('Pietro','Vaccaro','vacca@gmail.com','topogigio','PIEVAC00H23Z129G'); 
insert into utente values('Rosario','Muniz','rosario@gmail.com','topogigio','ROSMUN00H23Z129G'); 
insert into utente values('Vasco','Rossi','stonato@gmail.com','topogigio','VASROS00H23Z129G'); 
insert into utente values('Luigi','Rossi','luigi@gmail.com','topogigio','LUIROS00H23Z129G'); 
insert into utente values('Valerio','Scandalo','ohno@gmail.com','topogigio','VALSCA00H23Z129G'); 
insert into utente values('Ambrosio','Rossi','ambro@gmail.com','topogigio','AMBROS00H23Z129G'); 
insert into utente values('Vittorio','Rossi','stoato@gmail.com','topogigio','VITROS00H23Z129G'); 

--non è ancora solido, puo fare porcate ad esempio puoi decidere di non votare nessuno in una votazione ordinale e ti puoi candidare ad un referendum
--l'unico modo per renderlo solido è usare molti constraint e funzioni, farò in seguito
CREATE TABLE votazione(

    Proprietario char(16),
    Nome varchar(40) CHECK(length(Nome) >= 3),
    Id varchar(40), --devo trovare un modo per generarlo random con java
    Tipologia varchar(40) CHECK(Tipologia = 'ordinale' OR Tipologia = 'categorico' OR Tipologia = 'categorico con preferenze' OR Tipologia = 'referendum'),
    inizio timestamp NOT NULL,
    fine timestamp,
    Domanda varchar(300), --campo che penso possa tornare utile nel caso di un referendum
    PRIMARY KEY(Id),
    FOREIGN KEY(Proprietario)
        REFERENCES utente (codfiscale)

);


INSERT INTO votazione VALUES('STNLCU00H23Z129G', 'Elezione Comunale', '00102324', 'ordinale', '2021-12-14', '2021-12-30');--è il formato del cazzo americano, 12 è il mese(Dicembre)


CREATE TABLE candidati(
    CodFiscale char(16), --la chiave degli utenti
    Id varchar(40), -- è l'id della votazione
    PRIMARY KEY(CodFiscale, Id), --un candidato si puo candidare solo 1 volta ad una votazione
    FOREIGN Key(Id)
        REFERENCES votazione (Id),
    FOREIGN KEY(CodFiscale)
        REFERENCES utente(codfiscale)
);


INSERT INTO candidati VALUES('VASROS00H23Z129G', '00102324');
INSERT INTO candidati VALUES('ROSMUN00H23Z129G', '00102324');
INSERT INTO candidati VALUES('VITROS00H23Z129G', '00102324');

CREATE TABLE votanti(
    Votante char(16),
    Votato char(16),
    Id varchar(40),
    FavorevoleContrario boolean DEFAULT false,   --campo utile nel caso la votazione sia referendum
    Punteggio int  DEFAULT 0, --è un campo che ho pensato possa tornare utile nel caso la votazione sia ordinale
    PRIMARY KEY(Votante, Id), --una persona può votare solo 1 volta in una votazione, questo fa si che non possa esistere la votazione "categorica con preferenze", 
                              --attualmente non mi vengono in mente idee sul come implementarla senza dover fare nuove tabelle, devo usare una combo di constraint
                              --e funzioni postgress, DA FIXARE 
    FOREIGN Key(Id)
        REFERENCES votazione (Id),
    FOREIGN Key(Votante)
        REFERENCES utente (codfiscale),
    FOREIGN Key(Votato)
        REFERENCES utente (codfiscale)--in realtà dovrebbe avere come reference candidati(codfiscale) ma mi da errori del cazzo
                                        --facendo cosi si può votare qualcuno che non si è candidato, è fa fixare!
    
);

INSERT INTO votanti VALUES('VASROS00H23Z129G', 'VASROS00H23Z129G', '00102324'); --STA VOTANDO SE STESSO
INSERT INTO votanti VALUES('MRCPNC00H23Z129G', 'VASROS00H23Z129G', '00102324');
INSERT INTO votanti VALUES('MRCPNC00H23Z129G', 'ROSMUN00H23Z129G', '00102324'); --mi darà giustamente errore, non posso votare piu persone nella stessa votazione

