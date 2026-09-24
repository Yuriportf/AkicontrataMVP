-- ============================================================
-- MVPAkicontrata — Seed inicial
-- ============================================================

-- ------------------------------------------------------------
-- Roles
-- ------------------------------------------------------------
INSERT INTO role (nome) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_ALUNO'),
    ('ROLE_EMPRESA');

-- ------------------------------------------------------------
-- Instituição
-- ------------------------------------------------------------
INSERT INTO instituicao_ensino (nome, sigla, cidade, uf) VALUES
    ('Instituto Federal de Pernambuco', 'IFPE', 'Recife', 'PE');

-- ------------------------------------------------------------
-- Campus IFPE (16 presenciais)
-- ------------------------------------------------------------
INSERT INTO campus (nome, cidade, uf, tipo, instituicao_id) VALUES
    ('Campus Abreu e Lima',              'Abreu e Lima',              'PE', 'PRESENCIAL', 1),
    ('Campus Afogados da Ingazeira',     'Afogados da Ingazeira',     'PE', 'PRESENCIAL', 1),
    ('Campus Barreiros',                 'Barreiros',                 'PE', 'PRESENCIAL', 1),
    ('Campus Belo Jardim',               'Belo Jardim',               'PE', 'PRESENCIAL', 1),
    ('Campus Cabo de Santo Agostinho',   'Cabo de Santo Agostinho',   'PE', 'PRESENCIAL', 1),
    ('Campus Caruaru',                   'Caruaru',                   'PE', 'PRESENCIAL', 1),
    ('Campus Garanhuns',                 'Garanhuns',                 'PE', 'PRESENCIAL', 1),
    ('Campus Igarassu',                  'Igarassu',                  'PE', 'PRESENCIAL', 1),
    ('Campus Ipojuca',                   'Ipojuca',                   'PE', 'PRESENCIAL', 1),
    ('Campus Jaboatão dos Guararapes',   'Jaboatão dos Guararapes',   'PE', 'PRESENCIAL', 1),
    ('Campus Olinda',                    'Olinda',                    'PE', 'PRESENCIAL', 1),
    ('Campus Palmares',                  'Palmares',                  'PE', 'PRESENCIAL', 1),
    ('Campus Paulista',                  'Paulista',                  'PE', 'PRESENCIAL', 1),
    ('Campus Pesqueira',                 'Pesqueira',                 'PE', 'PRESENCIAL', 1),
    ('Campus Recife',                    'Recife',                    'PE', 'PRESENCIAL', 1),
    ('Campus Vitória de Santo Antão',    'Vitória de Santo Antão',    'PE', 'PRESENCIAL', 1);

-- ============================================================
-- TÉCNICOS INTEGRADOS
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Técnico em Administração',                         'TECNICO', 'TECNICO', 'Gestão e Negócios',                 6, 1),
    ('Técnico em Alimentos',                             'TECNICO', 'TECNICO', 'Produção Alimentícia',              6, 1),
    ('Técnico em Agricultura',                           'TECNICO', 'TECNICO', 'Recursos Naturais',                 6, 1),
    ('Técnico em Agroindústria',                         'TECNICO', 'TECNICO', 'Produção Alimentícia',              6, 1),
    ('Técnico em Agropecuária',                          'TECNICO', 'TECNICO', 'Recursos Naturais',                 6, 1),
    ('Técnico em Computação Gráfica',                    'TECNICO', 'TECNICO', 'Informação e Comunicação',          6, 1),
    ('Técnico em Edificações',                           'TECNICO', 'TECNICO', 'Infraestrutura',                    6, 1),
    ('Técnico em Eletrônica',                            'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  6, 1),
    ('Técnico em Eletrotécnica',                         'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  6, 1),
    ('Técnico em Eletroeletrônica',                      'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  6, 1),
    ('Técnico em Informática',                           'TECNICO', 'TECNICO', 'Informação e Comunicação',          6, 1),
    ('Técnico em Informática para Internet',             'TECNICO', 'TECNICO', 'Informação e Comunicação',          6, 1),
    ('Técnico em Manutenção e Suporte em Informática',   'TECNICO', 'TECNICO', 'Informação e Comunicação',          6, 1),
    ('Técnico em Mecânica',                              'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  6, 1),
    ('Técnico em Mecatrônica',                           'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  6, 1),
    ('Técnico em Meio Ambiente',                         'TECNICO', 'TECNICO', 'Ambiente e Saúde',                  6, 1),
    ('Técnico em Química',                               'TECNICO', 'TECNICO', 'Produção Industrial',               6, 1),
    ('Técnico em Refrigeração e Climatização',           'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  6, 1),
    ('Técnico em Saneamento',                            'TECNICO', 'TECNICO', 'Infraestrutura',                    6, 1),
    ('Técnico em Segurança do Trabalho',                 'TECNICO', 'TECNICO', 'Segurança',                         6, 1),
    ('Técnico em Telecomunicações',                      'TECNICO', 'TECNICO', 'Informação e Comunicação',          6, 1),
    ('Técnico em Zootecnia',                             'TECNICO', 'TECNICO', 'Recursos Naturais',                 6, 1);

-- ============================================================
-- TÉCNICOS SUBSEQUENTES (exclusivos)
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Técnico em Artes Visuais',                         'TECNICO', 'TECNICO', 'Produção Cultural e Design',        4, 1),
    ('Técnico em Automação Industrial',                  'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  4, 1),
    ('Técnico em Cozinha',                               'TECNICO', 'TECNICO', 'Turismo, Hospitalidade e Lazer',    4, 1),
    ('Técnico em Enfermagem',                            'TECNICO', 'TECNICO', 'Ambiente e Saúde',                  4, 1),
    ('Técnico em Hospedagem',                            'TECNICO', 'TECNICO', 'Turismo, Hospitalidade e Lazer',    4, 1),
    ('Técnico em Instrumento Musical',                   'TECNICO', 'TECNICO', 'Produção Cultural e Design',        4, 1),
    ('Técnico em Logística',                             'TECNICO', 'TECNICO', 'Gestão e Negócios',                 4, 1),
    ('Técnico em Qualidade',                             'TECNICO', 'TECNICO', 'Gestão e Negócios',                 4, 1),
    ('Técnico em Rede de Computadores',                  'TECNICO', 'TECNICO', 'Informação e Comunicação',          4, 1),
    ('Técnico em Sistemas de Energia Renovável',         'TECNICO', 'TECNICO', 'Controle e Processos Industriais',  4, 1);

-- ============================================================
-- TECNÓLOGOS
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Tecnólogo em Agroecologia',                          'TECNOLOGO', 'SUPERIOR', 'Recursos Naturais',                5, 1),
    ('Tecnólogo em Análise e Desenvolvimento de Sistemas', 'TECNOLOGO', 'SUPERIOR', 'Informação e Comunicação',         5, 1),
    ('Tecnólogo em Design Gráfico',                        'TECNOLOGO', 'SUPERIOR', 'Produção Cultural e Design',       5, 1),
    ('Tecnólogo em Gastronomia',                           'TECNOLOGO', 'SUPERIOR', 'Turismo, Hospitalidade e Lazer',   5, 1),
    ('Tecnólogo em Gestão Ambiental',                      'TECNOLOGO', 'SUPERIOR', 'Ambiente e Saúde',                 5, 1),
    ('Tecnólogo em Gestão da Qualidade',                   'TECNOLOGO', 'SUPERIOR', 'Gestão e Negócios',                5, 1),
    ('Tecnólogo em Gestão de Turismo',                     'TECNOLOGO', 'SUPERIOR', 'Turismo, Hospitalidade e Lazer',   5, 1),
    ('Tecnólogo em Gestão Hospitalar',                     'TECNOLOGO', 'SUPERIOR', 'Ambiente e Saúde',                 5, 1),
    ('Tecnólogo em Hotelaria',                             'TECNOLOGO', 'SUPERIOR', 'Turismo, Hospitalidade e Lazer',   5, 1),
    ('Tecnólogo em Processos Gerenciais',                  'TECNOLOGO', 'SUPERIOR', 'Gestão e Negócios',                5, 1),
    ('Tecnólogo em Produção Multimídia',                   'TECNOLOGO', 'SUPERIOR', 'Produção Cultural e Design',       5, 1),
    ('Tecnólogo em Radiologia',                            'TECNOLOGO', 'SUPERIOR', 'Ambiente e Saúde',                 5, 1),
    ('Tecnólogo em Sistemas para Internet',                'TECNOLOGO', 'SUPERIOR', 'Informação e Comunicação',         5, 1);

-- ============================================================
-- BACHARELADOS
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Bacharelado em Administração',                     'BACHARELADO', 'SUPERIOR', 'Gestão e Negócios',               8, 1),
    ('Bacharelado em Agronomia',                         'BACHARELADO', 'SUPERIOR', 'Recursos Naturais',              10, 1),
    ('Bacharelado em Enfermagem',                        'BACHARELADO', 'SUPERIOR', 'Ambiente e Saúde',               10, 1),
    ('Bacharelado em Engenharia Civil',                  'BACHARELADO', 'SUPERIOR', 'Infraestrutura',                 10, 1),
    ('Bacharelado em Engenharia Elétrica',               'BACHARELADO', 'SUPERIOR', 'Controle e Processos Industriais',10, 1),
    ('Bacharelado em Engenharia Mecânica',               'BACHARELADO', 'SUPERIOR', 'Controle e Processos Industriais',10, 1),
    ('Bacharelado em Engenharia Ambiental e Sanitária',  'BACHARELADO', 'SUPERIOR', 'Ambiente e Saúde',               10, 1),
    ('Bacharelado em Engenharia de Software',            'BACHARELADO', 'SUPERIOR', 'Informação e Comunicação',        8, 1);

-- ============================================================
-- LICENCIATURAS
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Licenciatura em Física',                           'LICENCIATURA', 'SUPERIOR', 'Ciências Exatas',                8, 1),
    ('Licenciatura em Geografia',                        'LICENCIATURA', 'SUPERIOR', 'Ciências Humanas',               8, 1),
    ('Licenciatura em Matemática',                       'LICENCIATURA', 'SUPERIOR', 'Ciências Exatas',                8, 1),
    ('Licenciatura em Música',                           'LICENCIATURA', 'SUPERIOR', 'Produção Cultural e Design',     8, 1),
    ('Licenciatura em Química',                          'LICENCIATURA', 'SUPERIOR', 'Ciências Exatas',                8, 1),
    ('Licenciatura em Computação',                       'LICENCIATURA', 'SUPERIOR', 'Informação e Comunicação',       8, 1);

-- ============================================================
-- ESPECIALIZAÇÕES
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Especialização em Educação do Campo',                              'ESPECIALIZACAO', 'POS_GRADUACAO', 'Educação',                              2, 1),
    ('Especialização em Ensino da Matemática para o Ensino Médio',       'ESPECIALIZACAO', 'POS_GRADUACAO', 'Educação',                              2, 1),
    ('Especialização em Gestão Pública',                                 'ESPECIALIZACAO', 'POS_GRADUACAO', 'Gestão e Negócios',                     2, 1),
    ('Especialização em Gestão e Qualidade em TIC',                      'ESPECIALIZACAO', 'POS_GRADUACAO', 'Informação e Comunicação',              2, 1),
    ('Especialização em Desenvolvimento, Inovação e Tecnologias Emergentes','ESPECIALIZACAO','POS_GRADUACAO','Informação e Comunicação',             2, 1),
    ('Especialização em Inovação e Desenvolvimento de Softwares para Web e Mobile','ESPECIALIZACAO','POS_GRADUACAO','Informação e Comunicação',        2, 1),
    ('Especialização em Ensino de Ciências',                             'ESPECIALIZACAO', 'POS_GRADUACAO', 'Educação',                              2, 1),
    ('Especialização em Matemática',                                     'ESPECIALIZACAO', 'POS_GRADUACAO', 'Ciências Exatas',                       2, 1),
    ('Especialização em Engenharia de Segurança do Trabalho',            'ESPECIALIZACAO', 'POS_GRADUACAO', 'Segurança',                             2, 1),
    ('Especialização em Gestão Estratégica em Logística',                'ESPECIALIZACAO', 'POS_GRADUACAO', 'Gestão e Negócios',                     2, 1),
    ('Especialização em Linguagem e Práticas Sociais',                   'ESPECIALIZACAO', 'POS_GRADUACAO', 'Ciências Humanas',                      2, 1),
    ('Especialização em Educação Ambiental e Cultural',                  'ESPECIALIZACAO', 'POS_GRADUACAO', 'Educação',                              2, 1),
    ('Especialização em Sustentabilidade Urbana',                        'ESPECIALIZACAO', 'POS_GRADUACAO', 'Ambiente e Saúde',                      2, 1),
    ('Especialização em Interdisciplinaridade em Educação e Ciências Humanas','ESPECIALIZACAO','POS_GRADUACAO','Educação',                           2, 1),
    ('Especialização em Docência para a Educação Profissional, Científica e Tecnológica','ESPECIALIZACAO','POS_GRADUACAO','Educação',                2, 1),
    ('Especialização em Práticas Interpretativas em Música Popular com Ênfase no Frevo','ESPECIALIZACAO','POS_GRADUACAO','Produção Cultural e Design',  2, 1),
    ('Especialização em Matemática Comercial, Contábil, Atuarial e Financeira','ESPECIALIZACAO','POS_GRADUACAO','Ciências Exatas',                   2, 1),
    ('Especialização em Ensino de Física e Matemática',                  'ESPECIALIZACAO', 'POS_GRADUACAO', 'Educação',                              2, 1),
    ('Especialização em Gestão de Saúde e Segurança Ocupacional',        'ESPECIALIZACAO', 'POS_GRADUACAO', 'Segurança',                             2, 1),
    ('Especialização em Enfermagem em Saúde da Família e Comunidade',    'ESPECIALIZACAO', 'POS_GRADUACAO', 'Ambiente e Saúde',                      2, 1),
    ('Especialização em Educação Intercultural Indígena-Quilombola Antirracista','ESPECIALIZACAO','POS_GRADUACAO','Educação',                          2, 1),
    ('Especialização em Educação Musical na Educação Básica',            'ESPECIALIZACAO', 'POS_GRADUACAO', 'Educação',                              2, 1),
    ('MBA em Empreendedorismo, Gestão e Inovação',                       'ESPECIALIZACAO', 'POS_GRADUACAO', 'Gestão e Negócios',                     2, 1),
    ('Especialização em Energia Solar Fotovoltaica',                     'ESPECIALIZACAO', 'POS_GRADUACAO', 'Controle e Processos Industriais',      2, 1),
    ('Especialização em Tecnologias Sustentáveis para as Ciências Agrárias','ESPECIALIZACAO','POS_GRADUACAO','Recursos Naturais',                    2, 1),
    ('Especialização em Ciências Humanas, Filosofia e Educação',         'ESPECIALIZACAO', 'POS_GRADUACAO', 'Ciências Humanas',                      2, 1);

-- ============================================================
-- MESTRADOS
-- ============================================================
INSERT INTO curso (nome, nivel, tipo_aluno, area, duracao_semestres, instituicao_id) VALUES
    ('Mestrado Profissional em Gestão Ambiental',                        'MESTRADO', 'POS_GRADUACAO', 'Ambiente e Saúde',                      4, 1),
    ('Mestrado Profissional em Educação Profissional e Tecnológica',     'MESTRADO', 'POS_GRADUACAO', 'Educação',                              4, 1),
    ('Mestrado Profissional em Filosofia',                               'MESTRADO', 'POS_GRADUACAO', 'Ciências Humanas',                      4, 1),
    ('Mestrado Profissional em Enfermagem',                              'MESTRADO', 'POS_GRADUACAO', 'Ambiente e Saúde',                      4, 1);

-- ============================================================
-- VÍNCULOS CURSO × CAMPUS
-- ============================================================

-- Técnicos Integrados
INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Administração' AND ca.nome = 'Campus Paulista';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Alimentos' AND ca.nome = 'Campus Barreiros';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Agricultura' AND ca.nome = 'Campus Vitória de Santo Antão';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Agroindústria' AND ca.nome IN ('Campus Belo Jardim','Campus Vitória de Santo Antão');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Agropecuária' AND ca.nome IN ('Campus Barreiros','Campus Belo Jardim','Campus Vitória de Santo Antão');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Computação Gráfica' AND ca.nome = 'Campus Olinda';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Edificações' AND ca.nome IN ('Campus Caruaru','Campus Pesqueira','Campus Recife');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Eletrônica' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Eletrotécnica' AND ca.nome IN ('Campus Pesqueira','Campus Recife');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Eletroeletrônica' AND ca.nome = 'Campus Garanhuns';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Informática' AND ca.nome IN ('Campus Afogados da Ingazeira','Campus Garanhuns');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Informática para Internet' AND ca.nome IN ('Campus Belo Jardim','Campus Palmares','Campus Paulista');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Manutenção e Suporte em Informática' AND ca.nome = 'Campus Vitória de Santo Antão';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Mecânica' AND ca.nome IN ('Campus Ipojuca','Campus Recife');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Mecatrônica' AND ca.nome = 'Campus Caruaru';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Meio Ambiente' AND ca.nome = 'Campus Garanhuns';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Química' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Refrigeração e Climatização' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Saneamento' AND ca.nome IN ('Campus Afogados da Ingazeira','Campus Recife');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Segurança do Trabalho' AND ca.nome IN ('Campus Caruaru','Campus Ipojuca','Campus Recife');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Telecomunicações' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Técnico em Zootecnia' AND ca.nome = 'Campus Vitória de Santo Antão';

-- Tecnólogos
INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Tecnólogo em Agroecologia' AND ca.nome = 'Campus Barreiros';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Tecnólogo em Análise e Desenvolvimento de Sistemas' AND ca.nome IN ('Campus Recife','Campus Belo Jardim');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Tecnólogo em Design Gráfico' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Tecnólogo em Gestão Ambiental' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Tecnólogo em Gestão de Turismo' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Tecnólogo em Radiologia' AND ca.nome = 'Campus Recife';

-- Bacharelados
INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Bacharelado em Agronomia' AND ca.nome = 'Campus Vitória de Santo Antão';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Bacharelado em Enfermagem' AND ca.nome = 'Campus Pesqueira';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Bacharelado em Engenharia Civil' AND ca.nome IN ('Campus Recife','Campus Afogados da Ingazeira');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Bacharelado em Engenharia Elétrica' AND ca.nome IN ('Campus Garanhuns','Campus Pesqueira');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Bacharelado em Engenharia Mecânica' AND ca.nome IN ('Campus Caruaru','Campus Recife');

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Bacharelado em Engenharia de Software' AND ca.nome = 'Campus Belo Jardim';

-- Mestrados
INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Mestrado Profissional em Gestão Ambiental' AND ca.nome = 'Campus Recife';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Mestrado Profissional em Educação Profissional e Tecnológica' AND ca.nome = 'Campus Olinda';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Mestrado Profissional em Filosofia' AND ca.nome = 'Campus Vitória de Santo Antão';

INSERT INTO curso_campus (curso_id, campus_id)
SELECT c.id, ca.id FROM curso c, campus ca
WHERE c.nome = 'Mestrado Profissional em Enfermagem' AND ca.nome = 'Campus Pesqueira';

-- ============================================================
-- FIM DO SEED
-- ============================================================
