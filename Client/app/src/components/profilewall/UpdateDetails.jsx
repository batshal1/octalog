import React, { Component } from "react";

import "./profile.scss";
import { Formik, Form, Field, ErrorMessage } from "formik";
import AuthenticationService from "../post/AuthenticationService";
import AccountProfileService from "../../api/main/AccountProfileService";

class UpdateDetails extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      firstname: this.props.firstname,
      lastname: this.props.lastname,
      email: this.props.email,
      phonenumber: this.props.phonenumber,
      aboutme: this.props.aboutme ? this.props.aboutme : "",
      isEmailDuplicate: false,
      isPhonenumberDuplicate: false,
      avatarloaded: false,
      backgroundloaded: false,
    };
    this.validate = this.validate.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.save = React.createRef();
  }

  componentDidMount() {
    this.refreshInfo();
  }

  refreshInfo() {
    let username = AuthenticationService.getLoggedInUserName();
    AccountProfileService.retrieveDetails(username).then((response) => {
      this.setState({
        firstname: response.data.firstname,
        lastname: response.data.lastname,
        email: response.data.email,
        phonenumber: response.data.phonenumber,
        aboutme: response.data.aboutme,
      });
    });
  }

  onSubmit(values) {
    let username = AuthenticationService.getLoggedInUserName();

    this.setState({
      firstname: values.firstname,
      lastname: values.lastname,
      email: values.email,
      phonenumber: values.phonenumber,
      aboutme: values.aboutme,
    });

    AccountProfileService.updateDetails(
      username,
      this.state.firstname,
      this.state.lastname,
      this.state.email,
      this.state.phonenumber,
      this.state.aboutme
    ).then(() => {
      this.refreshInfo();
      // this.props.triggerEditState();
      window.location.reload();
    });
  }

  checkDuplicateEmail(email) {
    if (email != null && email !== this.state.email) {
      AccountProfileService.checkDuplicateEmail(email).then((response) => {
        if (response.data == true) {
          this.setState({
            isEmailDuplicate: true,
          });
        } else if (response.data == false) {
          this.setState({
            isEmailDuplicate: false,
          });
        }
      });
    }
  }

  checkDuplicatePhonenumber(phonenumber) {
    if (phonenumber != null && phonenumber != this.state.phonenumber) {
      AccountProfileService.checkDuplicatePhonenumber(phonenumber).then(
        (response) => {
          if (response.data == true) {
            this.setState({
              isPhonenumberDuplicate: true,
            });
          } else if (response.data == false) {
            this.setState({
              isPhonenumberDuplicate: false,
            });
          }
        }
      );
    }
  }

  validate(values) {
    let errors = {};
    const nameCheck = /^[a-zA-Z\s]*$/;
    const phoneCheck = /^\+(?:[0-9] ?){6,14}[0-9]$/;
    const emailCheck = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

    this.checkDuplicateEmail(values.email);
    this.checkDuplicatePhonenumber(values.phonenumber);

    if (values.firstname == null) {
      errors.firstname = "Enter your first name";
    } else if (!nameCheck.test(values.firstname)) {
      errors.firstname = "Please enter a valid name";
    }

    if (values.lastname == null) {
      errors.lastname = "Enter your last name";
    } else if (!nameCheck.test(values.lastname)) {
      errors.lastname = "Please enter a valid name";
    }

    if (values.phonenumber == null) {
      errors.phonenumber = "Enter your phone number";
    } else if (!phoneCheck.test(values.phonenumber)) {
      errors.phonenumber = "Please enter a valid phone number";
    } else if (this.state.isPhonenumberDuplicate == true) {
      errors.phonenumber = "This phone number is already in use";
    }

    if (values.email == null) {
      errors.email = "Enter your email";
    } else if (!emailCheck.test(values.email)) {
      errors.email = "Please enter a valid email";
    } else if (this.state.isEmailDuplicate == true) {
      errors.email = "This email is already in use";
    }

    return errors;
  }

  render() {
    return (
      <div className="col-lg-4 row-md">
        <div className="ui-block">
          <div className="ui-title">
            <h5 style={{ marginBottom: 0 }}>Update Contact Details</h5>
          </div>
          <div className="ui-content">
            <div className="personal-info">
              <Formik
                onSubmit={this.onSubmit}
                initialValues={this.state}
                validateOnChange={this.validate}
                validateOnBlur={this.validate}
                validateOnSubmit={this.validate}
                validate={this.validate}
                enableReinitialize={false}
              >
                {(props) => (
                  <Form>
                    <fieldset className="form-group">
                      <label className="title">First name</label>
                      <Field
                        className="form-control"
                        type="text"
                        name="firstname"
                      />
                    </fieldset>
                    <ErrorMessage
                      name="firstname"
                      component="div"
                      className="alert alert-warning"
                    />

                    <fieldset className="form-group">
                      <label className="title">Last name</label>
                      <Field
                        className="form-control"
                        type="text"
                        name="lastname"
                      />
                    </fieldset>
                    <ErrorMessage
                      name="lastname"
                      component="div"
                      className="alert alert-warning"
                    />

                    <fieldset className="form-group">
                      <label className="title">Email</label>
                      <Field
                        className="form-control"
                        type="text"
                        name="email"
                      />
                    </fieldset>
                    <ErrorMessage
                      name="email"
                      component="div"
                      className="alert alert-warning"
                    />
                    <fieldset className="form-group">
                      <label className="title">Phone number</label>
                      <Field
                        className="form-control"
                        type="text"
                        name="phonenumber"
                      />
                    </fieldset>
                    <ErrorMessage
                      name="phonenumber"
                      component="div"
                      className="alert alert-warning"
                    />
                    <fieldset className="form-group">
                      <label className="title">About me</label>
                      <Field
                        className="form-control"
                        type="text"
                        name="aboutme"
                        component="textarea"
                      />
                    </fieldset>

                    <button
                      className="btn btn-success"
                      ref={this.save}
                      type="submit"
                    >
                      Save
                    </button>
                  </Form>
                )}
              </Formik>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UpdateDetails;
