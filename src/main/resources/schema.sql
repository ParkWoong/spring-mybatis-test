CREATE TABLE api_request_log (
    t_ymdhms VARCHAR(17),
    random_id VARCHAR(2),
    user_id VARCHAR(80),
    client_id VARCHAR(80),
    token_key_id VARCHAR(80),
    request_uri VARCHAR(1000),
    request_header VARCHAR(4000),
    request_body VARCHAR(4000),
    response_code VARCHAR(3)
);
CREATE TABLE client_backend_api (client_id VARCHAR(80), backend_api VARCHAR(1000));
INSERT INTO client_backend_api VALUES ('sample-client', 'http://localhost:9090');
