-- Insert a default user if it does not exist
INSERT INTO app_user (u_username, u_email, u_password, u_role)
SELECT 'root', 'root@furida.in', '$2b$12$bU2d91AuBWWNV6YcxA1.mOjduLvf9iRe3iHHvlFn2f5VJKeGGazXa', 'ADMIN'
WHERE NOT EXISTS (
  SELECT 1 FROM app_user WHERE u_username = 'root'
);
