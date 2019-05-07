CREATE TABLE CAR_BRAND (
     CAR_BRAND_ID BIGINT  NOT NULL AUTO_INCREMENT,
     CAR_BRAND_NAME VARCHAR(45),
     PRIMARY KEY (CAR_BRAND_ID)
);

CREATE TABLE  CAR_MODEL (
     CAR_MODEL_ID BIGINT  NOT NULL AUTO_INCREMENT,
     CAR_MODEL_NAME VARCHAR(50),
     CAR_BRAND_ID BIGINT,
     PRIMARY KEY (CAR_MODEL_ID),
     FOREIGN KEY (CAR_BRAND_ID) REFERENCES CAR_BRAND(CAR_BRAND_ID)
);

CREATE TABLE TYPE_FINES(
     TYPE_FINES_ID BIGINT  NOT NULL AUTO_INCREMENT,
     TYPE_FINES_NAME VARCHAR(80),
     PRIMARY KEY (TYPE_FINES_ID)
);

CREATE TABLE OWNERS (
     OWNER_ID BIGINT  NOT NULL AUTO_INCREMENT,
     OWNER_NAME VARCHAR(80),
     PRIMARY KEY(OWNER_ID)
);

CREATE TABLE LICENSE_PLATE_NUMBER (
     LICENSE_PLATE_NUMBER_ID BIGINT  NOT NULL AUTO_INCREMENT,
     CAR_BRAND_ID BIGINT,
     CAR_MODEL_ID BIGINT,
     OWNER_ID BIGINT,
     PRIMARY KEY (LICENSE_PLATE_NUMBER_ID),
     FOREIGN KEY (OWNER_ID) REFERENCES OWNERS(OWNER_ID),
     FOREIGN KEY (CAR_BRAND_ID) REFERENCES CAR_BRAND(CAR_BRAND_ID),
     FOREIGN KEY (CAR_MODEL_ID) REFERENCES CAR_MODEL(CAR_MODEL_ID)
);

CREATE TABLE FINES_INFORMATION (
     FINES_INFORMATION_ID BIGINT  NOT NULL AUTO_INCREMENT,
     LICENSE_PLATE_NUMBER_ID BIGINT,
     TYPE_FINES_ID BIGINT,
     PRIMARY KEY (FINES_INFORMATION_ID),
     FOREIGN KEY (TYPE_FINES_ID) REFERENCES TYPE_FINES (TYPE_FINES_ID),
     FOREIGN KEY (LICENSE_PLATE_NUMBER_ID) REFERENCES LICENSE_PLATE_NUMBER (LICENSE_PLATE_NUMBER_ID)
);

CREATE TABLE RECEIPT_PAYMENT_FINES (
     RECEIPT_PAYMENT_FINES_ID BIGINT  NOT NULL AUTO_INCREMENT,
     FINES_INFORMATION_ID BIGINT,
     RECEIPT_PAYMENT_FINES_STATUS_NAME VARCHAR(50),
     PRIMARY KEY (RECEIPT_PAYMENT_FINES_ID),
     FOREIGN KEY (FINES_INFORMATION_ID) REFERENCES FINES_INFORMATION(FINES_INFORMATION_ID)
);