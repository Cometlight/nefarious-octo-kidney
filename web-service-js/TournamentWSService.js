//
// Definitions for schema: http://ws.itb5c.fhv.at/
//  http://ec2-52-29-11-191.eu-central-1.compute.amazonaws.com:8080/jax-ws-server/TournamentWSService?xsd=1
//
//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}hasResultsResponse
//
function tournamentws_hasResultsResponse () {
    this.typeMarker = 'tournamentws_hasResultsResponse';
    this._return = '';
}

//
// accessor is tournamentws_hasResultsResponse.prototype.getReturn
// element get for return
// - element type is {http://www.w3.org/2001/XMLSchema}boolean
// - required element
//
// element set for return
// setter function is is tournamentws_hasResultsResponse.prototype.setReturn
//
function tournamentws_hasResultsResponse_getReturn() { return this._return;}

tournamentws_hasResultsResponse.prototype.getReturn = tournamentws_hasResultsResponse_getReturn;

function tournamentws_hasResultsResponse_setReturn(value) { this._return = value;}

tournamentws_hasResultsResponse.prototype.setReturn = tournamentws_hasResultsResponse_setReturn;
//
// Serialize {http://ws.itb5c.fhv.at/}hasResultsResponse
//
function tournamentws_hasResultsResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     xml = xml + '<return>';
     xml = xml + cxfjsutils.escapeXmlEntities(this._return);
     xml = xml + '</return>';
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_hasResultsResponse.prototype.serialize = tournamentws_hasResultsResponse_serialize;

function tournamentws_hasResultsResponse_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_hasResultsResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    var value = null;
    if (!cxfjsutils.isElementNil(curElement)) {
     value = cxfjsutils.getNodeText(curElement);
     item = (value == 'true');
    }
    newobject.setReturn(item);
    var item = null;
    if (curElement != null) {
     curElement = cxfjsutils.getNextElementSibling(curElement);
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}tournamentData
//
function tournamentws_tournamentData () {
    this.typeMarker = 'tournamentws_tournamentData';
    this._date = null;
    this._matches = [];
    this._name = null;
}

//
// accessor is tournamentws_tournamentData.prototype.getDate
// element get for date
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for date
// setter function is is tournamentws_tournamentData.prototype.setDate
//
function tournamentws_tournamentData_getDate() { return this._date;}

tournamentws_tournamentData.prototype.getDate = tournamentws_tournamentData_getDate;

function tournamentws_tournamentData_setDate(value) { this._date = value;}

tournamentws_tournamentData.prototype.setDate = tournamentws_tournamentData_setDate;
//
// accessor is tournamentws_tournamentData.prototype.getMatches
// element get for matches
// - element type is {http://ws.itb5c.fhv.at/}matchData
// - required element
// - array
// - nillable
//
// element set for matches
// setter function is is tournamentws_tournamentData.prototype.setMatches
//
function tournamentws_tournamentData_getMatches() { return this._matches;}

tournamentws_tournamentData.prototype.getMatches = tournamentws_tournamentData_getMatches;

function tournamentws_tournamentData_setMatches(value) { this._matches = value;}

tournamentws_tournamentData.prototype.setMatches = tournamentws_tournamentData_setMatches;
//
// accessor is tournamentws_tournamentData.prototype.getName
// element get for name
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for name
// setter function is is tournamentws_tournamentData.prototype.setName
//
function tournamentws_tournamentData_getName() { return this._name;}

tournamentws_tournamentData.prototype.getName = tournamentws_tournamentData_getName;

function tournamentws_tournamentData_setName(value) { this._name = value;}

tournamentws_tournamentData.prototype.setName = tournamentws_tournamentData_setName;
//
// Serialize {http://ws.itb5c.fhv.at/}tournamentData
//
function tournamentws_tournamentData_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._date != null) {
      xml = xml + '<date>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._date);
      xml = xml + '</date>';
     }
    }
    // block for local variables
    {
     if (this._matches != null) {
      for (var ax = 0;ax < this._matches.length;ax ++) {
       if (this._matches[ax] == null) {
        xml = xml + '<matches xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._matches[ax].serialize(cxfjsutils, 'matches', null);
       }
      }
     }
    }
    // block for local variables
    {
     if (this._name != null) {
      xml = xml + '<name>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._name);
      xml = xml + '</name>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_tournamentData.prototype.serialize = tournamentws_tournamentData_serialize;

function tournamentws_tournamentData_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_tournamentData();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing date');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'date')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing matches');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'matches')) {
     item = [];
     do  {
      var arrayItem = null;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = tournamentws_matchData_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'matches'));
     newobject.setMatches(item);
     var item = null;
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing name');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'name')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setName(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}hasResults
//
function tournamentws_hasResults () {
    this.typeMarker = 'tournamentws_hasResults';
    this._arg0 = null;
    this._arg1 = null;
    this._arg2 = null;
}

//
// accessor is tournamentws_hasResults.prototype.getArg0
// element get for arg0
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for arg0
// setter function is is tournamentws_hasResults.prototype.setArg0
//
function tournamentws_hasResults_getArg0() { return this._arg0;}

tournamentws_hasResults.prototype.getArg0 = tournamentws_hasResults_getArg0;

function tournamentws_hasResults_setArg0(value) { this._arg0 = value;}

tournamentws_hasResults.prototype.setArg0 = tournamentws_hasResults_setArg0;
//
// accessor is tournamentws_hasResults.prototype.getArg1
// element get for arg1
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for arg1
// setter function is is tournamentws_hasResults.prototype.setArg1
//
function tournamentws_hasResults_getArg1() { return this._arg1;}

tournamentws_hasResults.prototype.getArg1 = tournamentws_hasResults_getArg1;

function tournamentws_hasResults_setArg1(value) { this._arg1 = value;}

tournamentws_hasResults.prototype.setArg1 = tournamentws_hasResults_setArg1;
//
// accessor is tournamentws_hasResults.prototype.getArg2
// element get for arg2
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for arg2
// setter function is is tournamentws_hasResults.prototype.setArg2
//
function tournamentws_hasResults_getArg2() { return this._arg2;}

tournamentws_hasResults.prototype.getArg2 = tournamentws_hasResults_getArg2;

function tournamentws_hasResults_setArg2(value) { this._arg2 = value;}

tournamentws_hasResults.prototype.setArg2 = tournamentws_hasResults_setArg2;
//
// Serialize {http://ws.itb5c.fhv.at/}hasResults
//
function tournamentws_hasResults_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._arg0 != null) {
      xml = xml + '<arg0>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._arg0);
      xml = xml + '</arg0>';
     }
    }
    // block for local variables
    {
     if (this._arg1 != null) {
      xml = xml + '<arg1>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._arg1);
      xml = xml + '</arg1>';
     }
    }
    // block for local variables
    {
     if (this._arg2 != null) {
      xml = xml + '<arg2>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._arg2);
      xml = xml + '</arg2>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_hasResults.prototype.serialize = tournamentws_hasResults_serialize;

function tournamentws_hasResults_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_hasResults();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing arg0');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'arg0')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setArg0(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing arg1');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'arg1')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setArg1(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing arg2');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'arg2')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setArg2(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}getResultsResponse
//
function tournamentws_getResultsResponse () {
    this.typeMarker = 'tournamentws_getResultsResponse';
    this._return = null;
}

//
// accessor is tournamentws_getResultsResponse.prototype.getReturn
// element get for return
// - element type is {http://ws.itb5c.fhv.at/}tournamentResponse
// - optional element
//
// element set for return
// setter function is is tournamentws_getResultsResponse.prototype.setReturn
//
function tournamentws_getResultsResponse_getReturn() { return this._return;}

tournamentws_getResultsResponse.prototype.getReturn = tournamentws_getResultsResponse_getReturn;

function tournamentws_getResultsResponse_setReturn(value) { this._return = value;}

tournamentws_getResultsResponse.prototype.setReturn = tournamentws_getResultsResponse_setReturn;
//
// Serialize {http://ws.itb5c.fhv.at/}getResultsResponse
//
function tournamentws_getResultsResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._return != null) {
      xml = xml + this._return.serialize(cxfjsutils, 'return', null);
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_getResultsResponse.prototype.serialize = tournamentws_getResultsResponse_serialize;

function tournamentws_getResultsResponse_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_getResultsResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing return');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      item = tournamentws_tournamentResponse_deserialize(cxfjsutils, curElement);
     }
     newobject.setReturn(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}tournamentResponse
//
function tournamentws_tournamentResponse () {
    this.typeMarker = 'tournamentws_tournamentResponse';
    this._data = [];
}

//
// accessor is tournamentws_tournamentResponse.prototype.getData
// element get for data
// - element type is {http://ws.itb5c.fhv.at/}tournamentData
// - required element
// - array
// - nillable
//
// element set for data
// setter function is is tournamentws_tournamentResponse.prototype.setData
//
function tournamentws_tournamentResponse_getData() { return this._data;}

tournamentws_tournamentResponse.prototype.getData = tournamentws_tournamentResponse_getData;

function tournamentws_tournamentResponse_setData(value) { this._data = value;}

tournamentws_tournamentResponse.prototype.setData = tournamentws_tournamentResponse_setData;
//
// Serialize {http://ws.itb5c.fhv.at/}tournamentResponse
//
function tournamentws_tournamentResponse_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._data != null) {
      for (var ax = 0;ax < this._data.length;ax ++) {
       if (this._data[ax] == null) {
        xml = xml + '<data xsi:nil=\'true\'/>';
       } else {
        xml = xml + this._data[ax].serialize(cxfjsutils, 'data', null);
       }
      }
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_tournamentResponse.prototype.serialize = tournamentws_tournamentResponse_serialize;

function tournamentws_tournamentResponse_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_tournamentResponse();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing data');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'data')) {
     item = [];
     do  {
      var arrayItem = null;
      var value = null;
      if (!cxfjsutils.isElementNil(curElement)) {
       arrayItem = tournamentws_tournamentData_deserialize(cxfjsutils, curElement);
      }
      item.push(arrayItem);
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
       while(curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'data'));
     newobject.setData(item);
     var item = null;
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}getResults
//
function tournamentws_getResults () {
    this.typeMarker = 'tournamentws_getResults';
    this._arg0 = null;
    this._arg1 = null;
    this._arg2 = null;
}

//
// accessor is tournamentws_getResults.prototype.getArg0
// element get for arg0
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for arg0
// setter function is is tournamentws_getResults.prototype.setArg0
//
function tournamentws_getResults_getArg0() { return this._arg0;}

tournamentws_getResults.prototype.getArg0 = tournamentws_getResults_getArg0;

function tournamentws_getResults_setArg0(value) { this._arg0 = value;}

tournamentws_getResults.prototype.setArg0 = tournamentws_getResults_setArg0;
//
// accessor is tournamentws_getResults.prototype.getArg1
// element get for arg1
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for arg1
// setter function is is tournamentws_getResults.prototype.setArg1
//
function tournamentws_getResults_getArg1() { return this._arg1;}

tournamentws_getResults.prototype.getArg1 = tournamentws_getResults_getArg1;

function tournamentws_getResults_setArg1(value) { this._arg1 = value;}

tournamentws_getResults.prototype.setArg1 = tournamentws_getResults_setArg1;
//
// accessor is tournamentws_getResults.prototype.getArg2
// element get for arg2
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for arg2
// setter function is is tournamentws_getResults.prototype.setArg2
//
function tournamentws_getResults_getArg2() { return this._arg2;}

tournamentws_getResults.prototype.getArg2 = tournamentws_getResults_getArg2;

function tournamentws_getResults_setArg2(value) { this._arg2 = value;}

tournamentws_getResults.prototype.setArg2 = tournamentws_getResults_setArg2;
//
// Serialize {http://ws.itb5c.fhv.at/}getResults
//
function tournamentws_getResults_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._arg0 != null) {
      xml = xml + '<arg0>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._arg0);
      xml = xml + '</arg0>';
     }
    }
    // block for local variables
    {
     if (this._arg1 != null) {
      xml = xml + '<arg1>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._arg1);
      xml = xml + '</arg1>';
     }
    }
    // block for local variables
    {
     if (this._arg2 != null) {
      xml = xml + '<arg2>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._arg2);
      xml = xml + '</arg2>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_getResults.prototype.serialize = tournamentws_getResults_serialize;

function tournamentws_getResults_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_getResults();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing arg0');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'arg0')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setArg0(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing arg1');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'arg1')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setArg1(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing arg2');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'arg2')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setArg2(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Constructor for XML Schema item {http://ws.itb5c.fhv.at/}matchData
//
function tournamentws_matchData () {
    this.typeMarker = 'tournamentws_matchData';
    this._date = null;
    this._resultTeamOne = null;
    this._resultTeamTwo = null;
    this._teamOne = null;
    this._teamTwo = null;
}

//
// accessor is tournamentws_matchData.prototype.getDate
// element get for date
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for date
// setter function is is tournamentws_matchData.prototype.setDate
//
function tournamentws_matchData_getDate() { return this._date;}

tournamentws_matchData.prototype.getDate = tournamentws_matchData_getDate;

function tournamentws_matchData_setDate(value) { this._date = value;}

tournamentws_matchData.prototype.setDate = tournamentws_matchData_setDate;
//
// accessor is tournamentws_matchData.prototype.getResultTeamOne
// element get for resultTeamOne
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - optional element
//
// element set for resultTeamOne
// setter function is is tournamentws_matchData.prototype.setResultTeamOne
//
function tournamentws_matchData_getResultTeamOne() { return this._resultTeamOne;}

tournamentws_matchData.prototype.getResultTeamOne = tournamentws_matchData_getResultTeamOne;

function tournamentws_matchData_setResultTeamOne(value) { this._resultTeamOne = value;}

tournamentws_matchData.prototype.setResultTeamOne = tournamentws_matchData_setResultTeamOne;
//
// accessor is tournamentws_matchData.prototype.getResultTeamTwo
// element get for resultTeamTwo
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - optional element
//
// element set for resultTeamTwo
// setter function is is tournamentws_matchData.prototype.setResultTeamTwo
//
function tournamentws_matchData_getResultTeamTwo() { return this._resultTeamTwo;}

tournamentws_matchData.prototype.getResultTeamTwo = tournamentws_matchData_getResultTeamTwo;

function tournamentws_matchData_setResultTeamTwo(value) { this._resultTeamTwo = value;}

tournamentws_matchData.prototype.setResultTeamTwo = tournamentws_matchData_setResultTeamTwo;
//
// accessor is tournamentws_matchData.prototype.getTeamOne
// element get for teamOne
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for teamOne
// setter function is is tournamentws_matchData.prototype.setTeamOne
//
function tournamentws_matchData_getTeamOne() { return this._teamOne;}

tournamentws_matchData.prototype.getTeamOne = tournamentws_matchData_getTeamOne;

function tournamentws_matchData_setTeamOne(value) { this._teamOne = value;}

tournamentws_matchData.prototype.setTeamOne = tournamentws_matchData_setTeamOne;
//
// accessor is tournamentws_matchData.prototype.getTeamTwo
// element get for teamTwo
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for teamTwo
// setter function is is tournamentws_matchData.prototype.setTeamTwo
//
function tournamentws_matchData_getTeamTwo() { return this._teamTwo;}

tournamentws_matchData.prototype.getTeamTwo = tournamentws_matchData_getTeamTwo;

function tournamentws_matchData_setTeamTwo(value) { this._teamTwo = value;}

tournamentws_matchData.prototype.setTeamTwo = tournamentws_matchData_setTeamTwo;
//
// Serialize {http://ws.itb5c.fhv.at/}matchData
//
function tournamentws_matchData_serialize(cxfjsutils, elementName, extraNamespaces) {
    var xml = '';
    if (elementName != null) {
     xml = xml + '<';
     xml = xml + elementName;
     if (extraNamespaces) {
      xml = xml + ' ' + extraNamespaces;
     }
     xml = xml + '>';
    }
    // block for local variables
    {
     if (this._date != null) {
      xml = xml + '<date>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._date);
      xml = xml + '</date>';
     }
    }
    // block for local variables
    {
     if (this._resultTeamOne != null) {
      xml = xml + '<resultTeamOne>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._resultTeamOne);
      xml = xml + '</resultTeamOne>';
     }
    }
    // block for local variables
    {
     if (this._resultTeamTwo != null) {
      xml = xml + '<resultTeamTwo>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._resultTeamTwo);
      xml = xml + '</resultTeamTwo>';
     }
    }
    // block for local variables
    {
     if (this._teamOne != null) {
      xml = xml + '<teamOne>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._teamOne);
      xml = xml + '</teamOne>';
     }
    }
    // block for local variables
    {
     if (this._teamTwo != null) {
      xml = xml + '<teamTwo>';
      xml = xml + cxfjsutils.escapeXmlEntities(this._teamTwo);
      xml = xml + '</teamTwo>';
     }
    }
    if (elementName != null) {
     xml = xml + '</';
     xml = xml + elementName;
     xml = xml + '>';
    }
    return xml;
}

tournamentws_matchData.prototype.serialize = tournamentws_matchData_serialize;

function tournamentws_matchData_deserialize (cxfjsutils, element) {
    var newobject = new tournamentws_matchData();
    cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
    var curElement = cxfjsutils.getFirstElementChild(element);
    var item;
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing date');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'date')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setDate(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing resultTeamOne');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'resultTeamOne')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = parseInt(value);
     }
     newobject.setResultTeamOne(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing resultTeamTwo');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'resultTeamTwo')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = parseInt(value);
     }
     newobject.setResultTeamTwo(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing teamOne');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'teamOne')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setTeamOne(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
    cxfjsutils.trace('processing teamTwo');
    if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'teamTwo')) {
     var value = null;
     if (!cxfjsutils.isElementNil(curElement)) {
      value = cxfjsutils.getNodeText(curElement);
      item = value;
     }
     newobject.setTeamTwo(item);
     var item = null;
     if (curElement != null) {
      curElement = cxfjsutils.getNextElementSibling(curElement);
     }
    }
    return newobject;
}

//
// Definitions for schema: null
//  file:/C:/Users/Daniel/Downloads/apache-cxf-3.1.4/bin/TournamentWSService.wsdl.xml#types1
//
//
// Definitions for service: {http://ws.itb5c.fhv.at/}TournamentWSService
//

// Javascript for {http://ws.itb5c.fhv.at/}TournamentWS

function tournamentws_TournamentWS () {
    this.jsutils = new CxfApacheOrgUtil();
    this.jsutils.interfaceObject = this;
    this.synchronous = false;
    this.url = null;
    this.client = null;
    this.response = null;
    this.globalElementSerializers = [];
    this.globalElementDeserializers = [];
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}hasResultsResponse'] = tournamentws_hasResultsResponse_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}hasResultsResponse'] = tournamentws_hasResultsResponse_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}hasResults'] = tournamentws_hasResults_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}hasResults'] = tournamentws_hasResults_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}tournamentData'] = tournamentws_tournamentData_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}tournamentData'] = tournamentws_tournamentData_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}getResultsResponse'] = tournamentws_getResultsResponse_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}getResultsResponse'] = tournamentws_getResultsResponse_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}tournamentResponse'] = tournamentws_tournamentResponse_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}tournamentResponse'] = tournamentws_tournamentResponse_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}getResults'] = tournamentws_getResults_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}getResults'] = tournamentws_getResults_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}matchData'] = tournamentws_matchData_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}matchData'] = tournamentws_matchData_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}hasResultsResponse'] = tournamentws_hasResultsResponse_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}hasResultsResponse'] = tournamentws_hasResultsResponse_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}tournamentData'] = tournamentws_tournamentData_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}tournamentData'] = tournamentws_tournamentData_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}hasResults'] = tournamentws_hasResults_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}hasResults'] = tournamentws_hasResults_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}getResultsResponse'] = tournamentws_getResultsResponse_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}getResultsResponse'] = tournamentws_getResultsResponse_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}tournamentResponse'] = tournamentws_tournamentResponse_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}tournamentResponse'] = tournamentws_tournamentResponse_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}getResults'] = tournamentws_getResults_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}getResults'] = tournamentws_getResults_deserialize;
    this.globalElementSerializers['{http://ws.itb5c.fhv.at/}matchData'] = tournamentws_matchData_serialize;
    this.globalElementDeserializers['{http://ws.itb5c.fhv.at/}matchData'] = tournamentws_matchData_deserialize;
}

function tournamentws_hasResults_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling tournamentws_hasResultsResponse_deserializeResponse');
     responseObject = tournamentws_hasResultsResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

tournamentws_TournamentWS.prototype.hasResults_onsuccess = tournamentws_hasResults_op_onsuccess;

function tournamentws_hasResults_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     if (client.parseErrorDetails) {
      client.user_onerror(httpStatus, httpStatusText, client.parseErrorDetails(this));
     } else {
      client.user_onerror(httpStatus, httpStatusText);
     }
    }
}

tournamentws_TournamentWS.prototype.hasResults_onerror = tournamentws_hasResults_op_onerror;

//
// Operation {http://ws.itb5c.fhv.at/}hasResults
// Wrapped operation.
// parameter arg0
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter arg1
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter arg2
// - simple type {http://www.w3.org/2001/XMLSchema}string//
function tournamentws_hasResults_op(successCallback, errorCallback, arg0, arg1, arg2) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(3);
    args[0] = arg0;
    args[1] = arg1;
    args[2] = arg2;
    xml = this.hasResults_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.hasResults_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.hasResults_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

tournamentws_TournamentWS.prototype.hasResults = tournamentws_hasResults_op;

function tournamentws_hasResults_serializeInput(cxfjsutils, args) {
    var wrapperObj = new tournamentws_hasResults();
    wrapperObj.setArg0(args[0]);
    wrapperObj.setArg1(args[1]);
    wrapperObj.setArg2(args[2]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://ws.itb5c.fhv.at/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:hasResults', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

tournamentws_TournamentWS.prototype.hasResults_serializeInput = tournamentws_hasResults_serializeInput;

function tournamentws_hasResultsResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = tournamentws_hasResultsResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function tournamentws_getResults_op_onsuccess(client, responseXml) {
    if (client.user_onsuccess) {
     var responseObject = null;
     var element = responseXml.documentElement;
     this.jsutils.trace('responseXml: ' + this.jsutils.traceElementName(element));
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('first element child: ' + this.jsutils.traceElementName(element));
     while (!this.jsutils.isNodeNamedNS(element, 'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
      element = this.jsutils.getNextElementSibling(element);
      if (element == null) {
       throw 'No env:Body in message.'
      }
     }
     element = this.jsutils.getFirstElementChild(element);
     this.jsutils.trace('part element: ' + this.jsutils.traceElementName(element));
     this.jsutils.trace('calling tournamentws_getResultsResponse_deserializeResponse');
     responseObject = tournamentws_getResultsResponse_deserializeResponse(this.jsutils, element);
     client.user_onsuccess(responseObject);
    }
}

tournamentws_TournamentWS.prototype.getResults_onsuccess = tournamentws_getResults_op_onsuccess;

function tournamentws_getResults_op_onerror(client) {
    if (client.user_onerror) {
     var httpStatus;
     var httpStatusText;
     try {
      httpStatus = client.req.status;
      httpStatusText = client.req.statusText;
     } catch(e) {
      httpStatus = -1;
      httpStatusText = 'Error opening connection to server';
     }
     if (client.parseErrorDetails) {
      client.user_onerror(httpStatus, httpStatusText, client.parseErrorDetails(this));
     } else {
      client.user_onerror(httpStatus, httpStatusText);
     }
    }
}

tournamentws_TournamentWS.prototype.getResults_onerror = tournamentws_getResults_op_onerror;

//
// Operation {http://ws.itb5c.fhv.at/}getResults
// Wrapped operation.
// parameter arg0
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter arg1
// - simple type {http://www.w3.org/2001/XMLSchema}string// parameter arg2
// - simple type {http://www.w3.org/2001/XMLSchema}string//
function tournamentws_getResults_op(successCallback, errorCallback, arg0, arg1, arg2) {
    this.client = new CxfApacheOrgClient(this.jsutils);
    var xml = null;
    var args = new Array(3);
    args[0] = arg0;
    args[1] = arg1;
    args[2] = arg2;
    xml = this.getResults_serializeInput(this.jsutils, args);
    this.client.user_onsuccess = successCallback;
    this.client.user_onerror = errorCallback;
    var closureThis = this;
    this.client.onsuccess = function(client, responseXml) { closureThis.getResults_onsuccess(client, responseXml); };
    this.client.onerror = function(client) { closureThis.getResults_onerror(client); };
    var requestHeaders = [];
    requestHeaders['SOAPAction'] = '';
    this.jsutils.trace('synchronous = ' + this.synchronous);
    this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

tournamentws_TournamentWS.prototype.getResults = tournamentws_getResults_op;

function tournamentws_getResults_serializeInput(cxfjsutils, args) {
    var wrapperObj = new tournamentws_getResults();
    wrapperObj.setArg0(args[0]);
    wrapperObj.setArg1(args[1]);
    wrapperObj.setArg2(args[2]);
    var xml;
    xml = cxfjsutils.beginSoap11Message("xmlns:jns0='http://ws.itb5c.fhv.at/' ");
    // block for local variables
    {
     xml = xml + wrapperObj.serialize(cxfjsutils, 'jns0:getResults', null);
    }
    xml = xml + cxfjsutils.endSoap11Message();
    return xml;
}

tournamentws_TournamentWS.prototype.getResults_serializeInput = tournamentws_getResults_serializeInput;

function tournamentws_getResultsResponse_deserializeResponse(cxfjsutils, partElement) {
    var returnObject = tournamentws_getResultsResponse_deserialize (cxfjsutils, partElement);

    return returnObject;
}
function tournamentws_TournamentWS_tournamentws_TournamentWSPort () {
  this.url = 'http://ec2-52-29-176-124.eu-central-1.compute.amazonaws.com:8080/jax-ws-server/TournamentWSService';
}
tournamentws_TournamentWS_tournamentws_TournamentWSPort.prototype = new tournamentws_TournamentWS;
