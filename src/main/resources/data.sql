-- ===============================
-- GENRES
-- ===============================
INSERT INTO genres (name, description) VALUES
                                           ('Rock', 'Gitaar-gebaseerde muziek'),
                                           ('Pop', 'Popmuziek'),
                                           ('Electronic', 'Elektronische muziek');

-- ===============================
-- PUBLISHERS
-- ===============================
INSERT INTO publishers (name) VALUES
                                  ('Sony Music'),
                                  ('Universal Music Group'),
                                  ('Warner Music');

-- ===============================
-- ARTISTS
-- ===============================
INSERT INTO artists (name, biography) VALUES
                                          ('Daft Punk', 'Frans elektronisch duo'),
                                          ('Adele', 'Britse zangeres'),
                                          ('Nirvana', 'Amerikaanse grunge band');

-- ===============================
-- ALBUMS
-- LET OP: genre_id + publisher_id zijn verplicht
-- ===============================
INSERT INTO albums (title, release_year, genre_id, publisher_id) VALUES
                                                                     (
                                                                         'Random Access Memories',
                                                                         2013,
                                                                         (SELECT id FROM genres WHERE name = 'Electronic'),
                                                                         (SELECT id FROM publishers WHERE name = 'Sony Music')
                                                                     ),
                                                                     (
                                                                         '21',
                                                                         2011,
                                                                         (SELECT id FROM genres WHERE name = 'Pop'),
                                                                         (SELECT id FROM publishers WHERE name = 'Universal Music Group')
                                                                     ),
                                                                     (
                                                                         'Nevermind',
                                                                         1991,
                                                                         (SELECT id FROM genres WHERE name = 'Rock'),
                                                                         (SELECT id FROM publishers WHERE name = 'Warner Music')
                                                                     );

-- ===============================
-- STOCK
-- ===============================
INSERT INTO stock (condition, price, album_id) VALUES
                                                   (
                                                       'New',
                                                       34.99,
                                                       (SELECT id FROM albums WHERE title = 'Random Access Memories')
                                                   ),
                                                   (
                                                       'Used - Very Good',
                                                       24.50,
                                                       (SELECT id FROM albums WHERE title = 'Random Access Memories')
                                                   ),
                                                   (
                                                       'New',
                                                       29.99,
                                                       (SELECT id FROM albums WHERE title = '21')
                                                   ),
                                                   (
                                                       'Used - Good',
                                                       19.95,
                                                       (SELECT id FROM albums WHERE title = 'Nevermind')
                                                   );
