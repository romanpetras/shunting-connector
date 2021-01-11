insert into shuntingconn.simulation_requests (create_timestamp, create_user_id, create_user, update_timestamp, update_user_id, update_user, status)
values ('2020-12-16 12:20:57', '1', 'admin', '2020-12-16 12:25:21', '1', 'admin', 'SUBMITTED'),
       ('2020-12-16 12:20:57', '1', 'admin', '2020-12-16 12:25:21', '1', 'admin', 'SUBMITTED'),
       ('2020-12-16 12:20:57', '1', 'admin', '2020-12-16 12:25:21', '1', 'admin', 'PENDING'),
       ('2020-12-16 12:20:57', '1', 'admin', '2020-12-16 12:25:21', '1', 'admin', 'CANCELLED'),
       ('2020-12-16 12:20:57', '1', 'admin', '2020-12-16 12:25:21', '1', 'admin', 'DRAFT');

insert into shuntingconn.terminals (create_timestamp, create_user_id, create_user, update_timestamp, update_user_id, update_user, code, description) VALUES
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', 'IMT', 'Ignazio Messina Terminal');

insert into shuntingconn.wagon_types (create_timestamp, create_user_id, create_user, update_timestamp, update_user_id, update_user, code, description) VALUES
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', 'container', 'Container intermodale');

insert into shuntingconn.points (create_timestamp, create_user_id, create_user, update_timestamp, update_user_id, update_user, code, description, in_maneuver_park, locomotor_end, port_entrance, regression, stop) VALUES
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', 'S100', 'Punto di ingresso da linea sommergibile', false, false, true, false, false),
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', 'SE12', 'Binario 7 parco FM', false, false, true, false, false),
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', 'R1', 'Regresso fuori dal parco lato levante', false, false, true, false, false),
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', 'S100', 'Punto di sosta locomotiva elettrica', false, false, true, false, false);

insert into shuntingconn.main_maneuvers (create_timestamp, create_user_id, create_user, update_timestamp, update_user_id, update_user, eta, etp, maneuver_type, trace_number, wagons_number, arrival_point_id, locomotor_ending_point_id, maneuver_park_point_id, regression_point_id, simulation_request_id, terminal_id, wagon_type_id) VALUES
('2020-12-16 12:20:57', 1, 'admin', '2020-12-16 12:20:57', 1, 'admin', '2020-12-10 11:19:00', '2020-12-10 11:25:00', 'ARRIVAL', '52220', 9, 1, 4, 2, 3, 1, 1, 1);
