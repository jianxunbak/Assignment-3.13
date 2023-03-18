CREATE TABLE catalogue (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  price FLOAT,
  short_description VARCHAR(255),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE cart (
  id SERIAL PRIMARY KEY,
  catalogue_id INTEGER,
  quantity INTEGER,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY (catalogue_id) REFERENCES catalogue(id)
);

