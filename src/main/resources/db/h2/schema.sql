CREATE TABLE `member` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `gender` int NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `sector_code` int NOT NULL,
  `region_code` int NOT NULL
);

CREATE TABLE `region` (
  `region_code` int PRIMARY KEY,
  `region_name` varchar(255) NOT NULL
);

CREATE TABLE `sector` (
  `sector_code` int PRIMARY KEY,
  `sector_name` varchar(255) NOT NULL
);

ALTER TABLE `member` ADD FOREIGN KEY (`region_code`) REFERENCES `region` (`region_code`);
ALTER TABLE `member` ADD FOREIGN KEY (`sector_code`) REFERENCES `sector` (`sector_code`);
