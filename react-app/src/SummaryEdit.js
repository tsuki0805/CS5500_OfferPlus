import React from 'react';
import SummaryAdd from "./SummaryAdd";
import SummaryDelete from "./SummaryDelete";

const SummaryEdit = () => {
  return(
      <div>
        <SummaryDelete />
        <ul></ul>
        <SummaryAdd />
        <ul></ul>
      </div>
  );
};

export default SummaryEdit;