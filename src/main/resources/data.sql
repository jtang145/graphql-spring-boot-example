drop table if exists demo_pet;

CREATE TABLE demo_pet (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  age INTEGER default 0
);

INSERT INTO demo_pet (name, age) VALUES
('Tom', 3),
('Bill', 1),
('Jerry', 5),
('Added', 0);