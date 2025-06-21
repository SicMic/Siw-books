-- CREDEZIALI
INSERT INTO credenziali (id, username, ruolo, password) VALUES (1, 'lorenzo', 'ADMIN', '$2a$12$HtNgYhzZ0Gy.Si8DF9dHfOvTZBAc9E7c6rsqfBE90a3Mt21LZAkhi'); -- pw: "1Password!"
INSERT INTO credenziali (id, username, ruolo, password) VALUES (2, 'michela','ADMIN', '$2a$12$HtNgYhzZ0Gy.Si8DF9dHfOvTZBAc9E7c6rsqfBE90a3Mt21LZAkhi');
INSERT INTO credenziali (id, username, ruolo, password) VALUES (3, 'filippo', 'UTENTE', '$2a$12$HtNgYhzZ0Gy.Si8DF9dHfOvTZBAc9E7c6rsqfBE90a3Mt21LZAkhi');

-- UTENTE
INSERT INTO utente (id, credenziali_id, nome, cognome, email) VALUES (1, 1, 'Lorenzo', 'Riccio', 'lorenzo@example.com')
INSERT INTO utente (id, credenziali_id, nome, cognome, email) VALUES (2, 2, 'Michela', 'Sicu', 'mic@example.com')
INSERT INTO utente (id, credenziali_id, nome, cognome, email) VALUES (3, 3, 'Filippo', 'Maialino', 'filor@example.com')

-- AUTORI
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (1, 'Gabriel', 'Garcia Marquez', '1927-03-06', 'Colombia');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (2, 'J.K.', 'Rowling', '1965-07-31', 'Regno Unito');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (3, 'George', 'Orwell', '1903-06-25', 'Regno Unito');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (4, 'Harper', 'Lee', '1926-04-28', 'Stati Uniti');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (5, 'F. Scott', 'Fitzgerald', '1896-09-24', 'Stati Uniti');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (6, 'Mark', 'Twain', '1835-11-30', 'Stati Uniti');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (7, 'Jane', 'Austen', '1775-12-16', 'Regno Unito');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (8, 'Ernest', 'Hemingway', '1899-07-21', 'Stati Uniti');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (9, 'Leo', 'Tolstoy', '1828-09-09', 'Russia');
INSERT INTO autore (id, nome, cognome, data_nascita, nazione) VALUES (10, 'Virginia', 'Woolf', '1882-01-25', 'Regno Unito');

-- LIBRI
INSERT INTO libro (id, titolo, anno_pubblicazione, descrizione) VALUES (1, 'Cent`anni di solitudine', 1967, 'Molti anni dopo, davanti al plotone di esecuzione, il colonnello Aureliano Buendía avrebbe ricordato quel pomeriggio remoto in cui suo padre l`aveva portato a conoscere il ghiaccio. Macondo era allora un villaggio di venti case di fango e canne costruite sulla riva di un fiume dalle acque diafane che si precipitavano su un letto di pietre levigate, bianche ed enormi come uova preistoriche. Il mondo era così recente che molte cose erano senza nome, e per menzionarle bisognava indicarle col dito. Tutti gli anni, nel mese di marzo, una famiglia di zingari straccioni piantava la tenda vicino al villaggio, e con gran chiasso di fischietti e timbales veniva a far conoscere le nuove invenzioni. Prima portarono la calamita. Uno zingaro corpulento, con una barba selvatica e mani di passero, che si presentò col nome di Melquíades, diede una truce dimostrazione pubblica di quella che chiamava l`ottava meraviglia dei sapienti alchimisti di Macedonia. Andò di casa in casa trascinando due lingotti metallici, e tutti si spaventarono vedendo che paioli, padelle, pinze e fornelli cadevano in terra, e i legni scricchiolavano per la disperazione dei chiodi e delle viti che cercavano di schiodarsi, e anche gli oggetti perduti da molto tempo ricomparivano là dove più erano stati cercati, e strisciavano in un turbolento fuggifuggi dietro ai ferri magici di Melquíades. "Le cose hanno vita propria, "proclamava lo zingaro in tono aspro "è solo questione di risvegliarne l`anima.');
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (2, 'Harry Potter e la pietra filosofale', 1997);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (3, '1984', 1949);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (4, 'Il buio oltre la siepe', 1960);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (5, 'Il grande Gatsby', 1925);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (6, 'Le avventure di Tom Sawyer', 1876);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (7, 'Orgoglio e pregiudizio', 1813);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (8, 'Il vecchio e il mare', 1952);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (9, 'Guerra e pace', 1869);
INSERT INTO libro (id, titolo, anno_pubblicazione) VALUES (10, 'La signora Dalloway', 1925);

-- RECENSIONE
INSERT INTO recensione (id, titolo, testo, voto, libro_id, utente_id) VALUES (1, 'testo', 'Recensione', 1, 1, 1);


-- Associazione tra libri e autori
INSERT INTO libro_autori (libri_id, autori_id) VALUES (1, 1); -- Cent'anni di solitudine - Gabriel Garcia Marquez
INSERT INTO libro_autori (libri_id, autori_id) VALUES (2, 2); -- Harry Potter e la pietra filosofale - J.K. Rowling
INSERT INTO libro_autori (libri_id, autori_id) VALUES (3, 3); -- 1984 - George Orwell
INSERT INTO libro_autori (libri_id, autori_id) VALUES (4, 4); -- Il buio oltre la siepe - Harper Lee
INSERT INTO libro_autori (libri_id, autori_id) VALUES (5, 5); -- Il grande Gatsby - F. Scott Fitzgerald
INSERT INTO libro_autori (libri_id, autori_id) VALUES (6, 6); -- Le avventure di Tom Sawyer - Mark Twain
INSERT INTO libro_autori (libri_id, autori_id) VALUES (7, 7); -- Orgoglio e pregiudizio - Jane Austen
INSERT INTO libro_autori (libri_id, autori_id) VALUES (8, 8); -- Il vecchio e il mare - Ernest Hemingway
INSERT INTO libro_autori (libri_id, autori_id) VALUES (9, 9); -- Guerra e pace - Leo Tolstoy
INSERT INTO libro_autori (libri_id, autori_id) VALUES (10, 10); -- La signora Dalloway - Virginia Woolf

-- Aggiorna il counter degli id delle varie tabelle
SELECT setval('credenziali_seq', (SELECT MAX(id) FROM credenziali));
SELECT setval('utente_seq', (SELECT MAX(id) FROM utente));
SELECT setval('autore_seq', (SELECT MAX(id) FROM autore));
SELECT setval('libro_seq', (SELECT MAX(id) FROM libro));