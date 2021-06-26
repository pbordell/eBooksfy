import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';


class Home  extends Component {

    constructor(props) {
		document.title = "eBooksfy";
        super(props);
        this.state = {books: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/BookManager/books/')
            .then(response => response.json())
            .then(data => this.setState({books: data}));
    }

    async remove(id) {
        await fetch(`/BookManager/book/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedBooks = [...this.state.books].filter(i => i.id !== id);
            this.setState({books: updatedBooks});
        });
    }

    render() {
        const {books} = this.state;

        const bookList = books.map(book => {
            return <tr key={book.id}>
                <td style={{whiteSpace: 'nowrap'}}>{book.name}</td>
				<td>{book.year}</td>
                <td>{book.dateInclusion}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/books/" + book.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(book.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/books/new">Add Book</Button>
                    </div>
                    <h3>Books</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="10%">Year</th>
                            <th width="30%">Inclusion Date</th>
							<th width="30%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        {bookList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Home;