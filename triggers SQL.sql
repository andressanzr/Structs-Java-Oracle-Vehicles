CREATE OR REPLACE TRIGGER insertCamion
after insert on camiones
for each row
begin
    INSERT INTO matriculas VALUES(:new.cam.vehi.matricula);
END insertCamion;

CREATE OR REPLACE TRIGGER updateCamion
after update on camiones
for each row
begin
    UPDATE matriculas SET matricu=:new.cam.vehi.matricula;
END updateCamion;

CREATE OR REPLACE TRIGGER deleteCamion
after delete on camiones
for each row
begin
    DELETE FROM matriculas WHERE matricu =:old.cam.vehi.matricula;
END deleteCamion;

CREATE OR REPLACE TRIGGER insertTurismo
after insert on turismos
for each row
begin
    INSERT INTO matriculas VALUES(:new.tur.vehi.matricula);
END insertTurismo;

CREATE OR REPLACE TRIGGER updateTurismo
after update on turismos
for each row
begin
    UPDATE matriculas SET matricu = :new.tur.vehi.matricula WHERE matricu = :old.tur.vehi.matricula;
END updateTurismo;

CREATE OR REPLACE TRIGGER deleteTurismo
after delete on turismos
for each row
begin
    DELETE FROM matriculas WHERE matricu =:old.tur.vehi.matricula;
END deleteTurismo;