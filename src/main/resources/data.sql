INSERT INTO app_user (u_username, u_email, u_password, u_role, u_create_date)
VALUES (
    'root', 
    'root@furida.in', 
    '$2b$12$bU2d91AuBWWNV6YcxA1.mOjduLvf9iRe3iHHvlFn2f5VJKeGGazXa', 
    'ADMIN', 
    CURRENT_TIMESTAMP
)
ON CONFLICT (u_username) DO NOTHING;
