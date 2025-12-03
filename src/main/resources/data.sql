-- Genres
INSERT INTO genres (id, create_date, edit_date, name, description) VALUES
       (1, now(), now(), 'Rock',         'Muziek met elektrische gitaren en drums'),
       (2, now(), now(), 'Pop',          'Populaire muziek, vaak in de hitlijsten'),
       (3, now(), now(), 'Jazz',         'Geïmproviseerde muziek met swing'),
       (4, now(), now(), 'Hip-hop',      'Ritmische muziek met rap en beats'),
       (5, now(), now(), 'Klassiek',     'Symfonieën en orkestmuziek');

-- Publishers
INSERT INTO publishers (id, create_date, edit_date, name, address, contact_details) VALUES
        (1, now(), now(), 'Universal Music Group',   'Singel 100, Amsterdam',      'info@umusic.com, 020-1234567'),
        (2, now(), now(), 'Sony Music Entertainment','Blaak 555, Rotterdam',       'contact@sonymusic.com, 010-6543210'),
        (3, now(), now(), 'Warner Music Group',      'Keizersgracht 789, Utrecht', 'info@warnermusic.com, 030-9876543');
