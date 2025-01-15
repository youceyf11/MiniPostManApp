import './App.css';
import { useState } from 'react';
import axios from 'axios';
import { Canvas } from '@react-three/fiber';
import { OrbitControls, Stars } from '@react-three/drei';

function App() {
    const [url, setUrl] = useState('');
    const [body, setBody] = useState('');
    const [requestType, setRequestType] = useState('GET');
    const [headers, setHeaders] = useState([{ key: '', value: '' }]);

    const addHeader = () => {
        setHeaders([...headers, { key: '', value: '' }]);
    };

    const updateHeader = (index: number, field: string, value: string) => {
        const newHeaders = headers.map((header, i) =>
            i === index ? { ...header, [field]: value } : header
        );
        setHeaders(newHeaders);
    };

    const removeHeader = (index: number) => {
        setHeaders(headers.filter((_, i) => i !== index));
    };

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        const requestData = {
            url,
            headers: headers.reduce((acc: { [key: string]: string }, header) => {
                acc[header.key] = header.value;
                return acc;
            }, {}),
            body,
            requestType
        };
        axios.post('http://localhost:8080/api/v1/request', requestData)
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    };

    return (
        <div className="background">
            <Canvas className="canvas">
                <OrbitControls />
                <Stars />
                <ambientLight intensity={0.5} />
                <pointLight position={[10, 10, 10]} />
            </Canvas>
            <div className="content">
                <form onSubmit={handleSubmit}>
                    <select name="RequestType" value={requestType} onChange={(e) => setRequestType(e.target.value)}>
                        <option value="GET">GET</option>
                        <option value="POST">POST</option>
                        <option value="PUT">PUT</option>
                        <option value="DELETE">DELETE</option>
                    </select>
                    <div>
                        <label>URL:</label>
                        <input type="text" name="url" value={url} onChange={(e) => setUrl(e.target.value)} required />
                    </div>
                    <div>
                        <label>Headers:</label>
                        <button type="button" onClick={addHeader}>Add Header</button>
                        {headers.map((header, index) => (
                            <div key={index}>
                                <input
                                    type="text"
                                    placeholder="Header Key"
                                    value={header.key}
                                    onChange={(e) => updateHeader(index, 'key', e.target.value)}
                                />
                                <input
                                    type="text"
                                    placeholder="Header Value"
                                    value={header.value}
                                    onChange={(e) => updateHeader(index, 'value', e.target.value)}
                                />
                                <button type="button" onClick={() => removeHeader(index)}>Remove</button>
                            </div>
                        ))}
                    </div>
                    <div>
                        <label>Body (JSON format):</label>
                        <textarea name="body" value={body} onChange={(e) => setBody(e.target.value)}></textarea>
                    </div>
                    <button type="submit">Submit</button>
                </form>
                <div>
                    <h2>Response</h2>
                    <div id="response"></div>
                </div>
            </div>
        </div>
    );
}

export default App;
export interface Request {
    url: string;
    headers: Map<string, string>;
    body: string;
    RequestType: string;
}
