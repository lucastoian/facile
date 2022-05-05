
/* funzione che solleva un eccezione se NONI si prova a votare tra la data di inizio e quella di fine
CREATE OR REPLACE FUNCTION checkTheTime()
RETURNS trigger AS
$$
BEGIN
		IF NOT EXISTS(SELECT *
					 FROM votazione
					 WHERE votazione.id = NEW.id AND NEW."dataDiVoto" > votazione.inizio AND NEW."dataDiVoto" < votazione.fine)
		THEN
		RAISE EXCEPTION 'Non si può votare in questa data';
		END IF;
		RETURN NEW;
END;
$$
LANGUAGE plpgsql;

create trigger checkDateTrigger
before insert
on votanti
for each row
execute procedure checkTheTime();


CREATE OR REPLACE FUNCTION calcolaPunteggioCandidato()
RETURNS trigger AS
$$
DECLARE
    SommaPunteggio integer;
BEGIN
    SommaPunteggio = (SELECT punteggio FROM candidati WHERE candidati.codfiscale = NEW.votato AND candidati.id = NEW.id);
    UPDATE candidati
    SET punteggio = SommaPunteggio + NEW.punteggio
    WHERE codfiscale = NEW.votato AND id = NEW.id;

    RETURN NEW;

END;
$$
LANGUAGE plpgsql;

create trigger updatePunteggioCandidato
before insert
on votanti
for each row
execute procedure calcolaPunteggioCandidato();