import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';

class BookEdit extends Component {

    emptyItem = {
        name: '',
        year: ''
    };
	
	constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
	
	async componentDidMount() {
		if (this.props.match.params.id !== 'new') {
			const book = await (await fetch(`/BookManager/book/${this.props.match.params.id}`)).json();
			this.setState({item: book});
		}
	}
	
	handleChange(event) {
		const target = event.target;
		const value = target.value;
		const name = target.name;
		let item = {...this.state.item};
		console.log(target.checked);
		if (name === 'read') {
			item[name] = target.checked
		} else {
			item[name] = value;
		}
		this.setState({item});
	}

	async handleSubmit(event) {
		event.preventDefault();
		const {item} = this.state;


		await fetch('/BookManager/book/' + (item.id ? item.id : ''), {
			method: (item.id) ? 'PUT' : 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(item),
		});
		this.props.history.push('/');
	}

	render() {
		const {item} = this.state;
		const title = <h2>{item.id ? 'Edit Book' : 'Add Book'}</h2>;

		return <div>
			<AppNavbar/>
			<Container>
				{title}
				<Form onSubmit={this.handleSubmit}>
					<FormGroup>
						<Label for="name">Name *</Label>
						<Input type="text" name="name" id="name" value={item.name || ''} onChange={this.handleChange} required/>
					</FormGroup>
					<FormGroup>
						<Label for="year">Year</Label>
						<Input type="text" name="year" id="year" value={item.year || ''} onChange={this.handleChange} pattern="\d*" maxlength="4" />
					</FormGroup>
					<div class="form-check">
						<Input type="checkbox" class="form-check-label" name="read" id="read" checked={item.read} onChange={this.handleChange} />
						<Label class="form-check-label" for="read">Read</Label>
					</div>
					<FormGroup>
						<Button color="primary" type="submit">Save</Button>{' '}
						<Button color="secondary" tag={Link} to="/">Cancel</Button>
					</FormGroup>
				</Form>
			</Container>
		</div>
	}
}

export default withRouter(BookEdit);