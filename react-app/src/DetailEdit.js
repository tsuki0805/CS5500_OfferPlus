import React from 'react';
import DetailAdd from "./DetailAdd";
import DetailDelete from "./DetailDelete";
import DetailUpdate from "./DetailUpdate";


const DetailEdit = () => {
  return(
      <div>
        <DetailDelete />
        <ul></ul>
        <DetailAdd />
        <ul></ul>
        {/*<DetailUpdate />*/}
      </div>
  );
};

export default DetailEdit;