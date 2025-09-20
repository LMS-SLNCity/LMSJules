-- Database schema for the Lab Operations Software
-- Based on the design document

CREATE TABLE referral_doctors (
    doctor_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    clinic_hospital VARCHAR(255),
    phone VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE visits (
    visit_id SERIAL PRIMARY KEY,
    salutation VARCHAR(20) NOT NULL,
    name VARCHAR(255) NOT NULL,
    age_years INT NOT NULL,
    age_months INT,
    age_days INT,
    sex VARCHAR(10) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    date_of_visit DATE DEFAULT CURRENT_DATE,
    referred_doctor_id INT REFERENCES referral_doctors(doctor_id),
    visit_code VARCHAR(50) UNIQUE,
    created_at TIMESTAMP DEFAULT NOW(),
    status VARCHAR(50) DEFAULT 'pending'
);

CREATE TABLE test_templates (
    template_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    parameters JSONB NOT NULL,
    base_price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE lab_tests (
    test_id SERIAL PRIMARY KEY,
    visit_id INT NOT NULL REFERENCES visits(visit_id),
    test_template_id INT NOT NULL REFERENCES test_templates(template_id),
    status VARCHAR(50) DEFAULT 'pending',
    price DECIMAL(10,2) NOT NULL,
    results JSONB,
    approved BOOLEAN DEFAULT FALSE,
    approved_by VARCHAR(255),
    approved_at TIMESTAMP
);

CREATE TABLE antibiotics (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE culture_sensitivity_results (
    id SERIAL PRIMARY KEY,
    visit_id INT NOT NULL REFERENCES visits(visit_id),
    test_id INT NOT NULL REFERENCES lab_tests(test_id),
    antibiotic_id INT NOT NULL REFERENCES antibiotics(id),
    sensitivity VARCHAR(20) CHECK (sensitivity IN ('Resistant','Sensitive','Moderate'))
);

CREATE TABLE invoices (
    invoice_id SERIAL PRIMARY KEY,
    visit_id INT NOT NULL REFERENCES visits(visit_id),
    total_amount DECIMAL(10,2) NOT NULL,
    discount_request NUMERIC(10,2),
    discount_approved NUMERIC(10,2),
    discount_status VARCHAR(20) CHECK (discount_status IN ('None','Requested','Approved','Rejected')) DEFAULT 'None',
    approved_by VARCHAR(255),
    payment_mode VARCHAR(20),
    paid BOOLEAN DEFAULT FALSE,
    report_pdf_path TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);
