ALTER TABLE message
RENAME COLUMN m_date TO m_send_date;

ALTER TABLE post
RENAME COLUMN p_date TO p_create_date;

ALTER TABLE app_user
ADD COLUMN u_create_date TIMESTAMP(6);

UPDATE app_user
SET u_create_date = CURRENT_TIMESTAMP;

ALTER TABLE document
ADD COLUMN d_create_date TIMESTAMP(6);

UPDATE document
SET d_create_date = CURRENT_TIMESTAMP;

ALTER TABLE project
ADD COLUMN pj_create_date TIMESTAMP(6);

UPDATE project
SET pj_create_date = CURRENT_TIMESTAMP;
